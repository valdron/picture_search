
for file in * 
do
if [ -d "$file" ]; then # if it is a directory it is a subproject
cd $file
    if [ -e "./build.sh" ]; then
        ./build.sh
    fi
cd -
fi
done