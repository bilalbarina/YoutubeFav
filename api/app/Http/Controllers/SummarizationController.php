<?php

namespace App\Http\Controllers;

use App\Models\Summarization;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Http;

class SummarizationController extends Controller
{
    protected function summarize(Request $request)
    {
        $request->validate([
            'url' => ['required', 'url'],
        ]);
        set_time_limit(1300);

        $videoURL = $request->input('url');
        preg_match("/\?v=([-0-9a-zA-Z]*)/", $videoURL, $videoIdMatch);
        $videoId = @$videoIdMatch[1];

        $summarization = Summarization::where('video_id', $videoId)->first();
        if ($summarization) {
            $summary = $summarization->summary;
        } else {
            $req = Http::timeout(1200)->withHeaders([
                'openai-api-key' => env('OPENAI_APIKEY'),
                'X-RapidAPI-Key' => env('RAPIDAPI_KEY'),
                'X-RapidAPI-Host' => 'youtube-video-summarizer1.p.rapidapi.com',
            ])->get('https://youtube-video-summarizer1.p.rapidapi.com/v1/youtube/summarizeVideoWithToken', [
                'videoURL' => $videoURL,
            ]);

            // !is_null($summary = $req->json('summary'))
            if ($req->ok() && $req->status() == 200 && !is_null($summary = $req->json('summary'))) {
                Summarization::updateOrCreate(
                    ['video_id' => $videoId],
                    [
                        'summary' => $summary,
                        'user_id' => auth()->user()?->id,
                    ]
                );
            }
        }

        return response()->json([
            'success' => true,
            'summary' => $this->format($summary ?? "", $videoId),
        ]);
    }

    protected function format(string $text, $videoId)
    {
        $re = '/##\s(.*?)(\((https:\/\/.*?)(t=[0-9]*s)\)|)\s-\s/m';
        $subst = "<br><br><a class=\"text-blue-600\" target=\"_blank\" href=\"https://www.youtube.com/watch?v=" . $videoId . "&$4\">$1</a><br>";
        $result = preg_replace($re, $subst, $text);

        return $result;
    }
}
