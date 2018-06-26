cmake_minimum_required(VERSION 3.10)
project(ffplay C)

set(CMAKE_C_STANDARD 99)
include_directories(/usr/local/Cellar/ffmpeg/3.4.2/include/)
link_directories(/usr/local/Cellar/ffmpeg/3.4.2/lib/)

include_directories(/usr/local/Cellar/sdl2/2.0.8/include/SDL2)
link_directories(/usr/local/Cellar/sdl2/2.0.8/lib/)

add_executable(ffplay ffplay.c )

target_link_libraries(
        ffplay
        SDL2
        SDL2main
        avcodec
        avdevice
        avfilter
        avformat
        avresample
        avutil
        postproc
        swresample
        swscale
)