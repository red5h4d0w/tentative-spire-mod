for i in *84.png; do
    ffmpeg -i "$i" -vf "scale=32:32" -sws_flags bicubic -y "${i::-6}32.png"
done