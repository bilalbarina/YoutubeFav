<?php

namespace App\Http\Controllers;

use App\Helpers\YouTube;
use App\Http\Resources\FavoriteResource;
use App\Models\Favorite;
use Illuminate\Http\Request;

class FavoriteController extends Controller
{
    /**
     * Return all favorites.
     * 
     */
    public function all()
    {
        $favorites = Favorite::all();
        return FavoriteResource::collection($favorites);
    }

    /**
     * Store a video to favorites.
     * 
     */
    public function store(Request $request)
    {
        $request->validate([
            'video_id' => ['required', 'alpha_dash'],
            // 'video_title' => ['required', 'string'],
            // 'video_description' => ['nullable', 'sometimes', 'string'],
            // 'video_thumb' => ['nullable', 'sometimes', 'url'],
        ]);

        $videoId = $request->video_id;

        if ($request->has('video_title')) {
            $data = [
                'video_id' => $request->video_id,
                'video_title' => $request->video_title,
                'video_thumb' => $request->video_thumb,
            ];
        } else {
            $youtubeData = YouTube::getVideo($videoId);
            $videoTitle = $youtubeData['snippet']['title'];
            $videoThumb = $youtubeData['snippet']['thumbnails']['high']['url'];

            $data = [
                'video_id' => $request->video_id,
                'video_title' => $videoTitle,
                'video_thumb' => $videoThumb,
            ];
        }

        $data['user_id'] = auth()->user()?->id;

        Favorite::create($data);

        return response()->json([
            'success' => true,
            'favorites' => $this->all(),
        ]);
    }

    public function remove(Favorite $favorite)
    {
        $favorite->delete();
        return response()->json([
            'success' => true
        ]);
    }
}
