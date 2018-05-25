
for file in * 
do
if [ -d "$file" ]; then # if it is a directory it is a subproject
cd $file
    ./build.sh
cd -
fi
done