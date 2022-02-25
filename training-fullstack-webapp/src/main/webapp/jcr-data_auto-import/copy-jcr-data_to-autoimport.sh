#!/bin/sh

#setting variables
base_dir=../
auto_import_dir=${base_dir}jcr-data_auto-import
mandatory_foldername=jcr-data_mandatory
samples_foldername=jcr-data_samples

if [ "$1" = "mandatory" -o "$1" = "samples" -o "$1" = "all" ] ; then

    if [ "$1" = "mandatory" -o "$1" = "all" ] ; then
        find $base_dir -name $mandatory_foldername -exec cp -r {}/ $auto_import_dir \;
    fi
    if [ "$1" = "samples" -o "$1" = "all" ] ; then
        find $base_dir -name $samples_foldername -exec cp -r {}/ $auto_import_dir \;
    fi
    
else
    echo "Please provide \"mandatory\" or \"samples\" of \"all\" as argument."
    exit 1
fi
