<?php

namespace App\Http\Resources;

use Illuminate\Http\Resources\Json\JsonResource;

class FavoriteResource extends JsonResource
{
    /**
     * Transform the resource into an array.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return array|\Illuminate\Contracts\Support\Arrayable|\JsonSerializable
     */
    public function toArray($request)
    {
        return [
            'video_id' => $this->video_id,
            'video_title' => $this->video_title,
            'video_description' => $this->video_description,
            'video_thumb' => $this->video_thumb,
            'added_at' => $this->created_at,
        ];
    }
}
