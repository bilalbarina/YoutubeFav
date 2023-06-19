<?php

namespace App\Helpers;

use Illuminate\Support\Facades\Http;

class YouTube {
    public static function getVideo($videoId)
    {
        $url = 'https://youtube.googleapis.com/youtube/v3/videos' . '?' . http_build_query([
            'part' => 'snippet',
            'id' => $videoId,
            'key' => 'AIzaSyB6lHRAC6dht0pR14Rx-w79kQwnSmXwHRc'
        ]);

        $req = Http::get($url);

        if ($req->ok()) {
            return $req->json('items')[0];
        }

        return null;
    }
}