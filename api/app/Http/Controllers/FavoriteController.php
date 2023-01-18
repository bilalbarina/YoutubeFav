<?php

namespace App\Http\Controllers;

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
            'video_title' => ['required', 'string'],
            'video_thumb' => ['required', 'url'],
        ]);

        Favorite::create([
            'video_id' => $request->video_id,
            'video_title' => $request->video_title,
            'video_thumb' => $request->video_thumb,
        ]);

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
