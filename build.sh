
for file in * 
do
if [ -d "$file" ]; then
cd $file
    ./build.sh
cd -
fi
done