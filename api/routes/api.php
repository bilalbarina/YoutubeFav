<?php

use App\Http\Controllers\API\AuthController;
use App\Http\Controllers\FavoriteController;
use App\Http\Controllers\SummarizationController;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;
// use AuthCon

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

// Auth routes
Route::post('/auth/register', [AuthController::class, 'createUser'])->name('register');
Route::post('/auth/login', [AuthController::class, 'loginUser'])->name('login');

// Route::middleware('auth:sanctum')->group(function () {
    Route::controller(FavoriteController::class)->prefix('favorite')->group(function () {
        Route::get('/', 'all');
        Route::post('/store', 'store');
        Route::delete('/remove/{favorite:video_id}', 'remove');
    });

    Route::controller(SummarizationController::class)->prefix('services')->group(function () {
        Route::post('/summarize', 'summarize');
    });
// });
