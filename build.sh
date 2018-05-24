
for dir in * 
do
if [ -d "$dir" ]; then
cd $dir
    ./build.sh
cd -
fi
done