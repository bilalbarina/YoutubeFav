<?php

use App\Http\Controllers\FavoriteController;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/

Route::controller(FavoriteController::class)->prefix('favorite')->group(function() {
    Route::get('/', 'all');
    Route::post('/store', 'store');
    Route::delete('/remove/{favorite:video_id}', 'remove');
});