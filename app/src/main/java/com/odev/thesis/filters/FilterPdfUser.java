package com.odev.thesis.filters;

import android.widget.Filter;

import com.odev.thesis.adapters.AdapterPdfUser;
import com.odev.thesis.models.PdfModel;

import java.lang.reflect.Modifier;
import java.util.ArrayList;

public class FilterPdfUser extends Filter {

    //arraylist in which we want to search
    ArrayList<PdfModel> filterList;
    AdapterPdfUser adapterPdfUser;

    public FilterPdfUser(ArrayList<PdfModel> filterList, AdapterPdfUser adapterPdfUser) {
        this.filterList = filterList;
        this.adapterPdfUser = adapterPdfUser;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results = new FilterResults();
        //value should not be null and empty
        if (constraint != null && constraint.length() > 0){
            //change to upper case to avoid case sensitivity
            constraint = constraint.toString().toUpperCase();
            ArrayList<PdfModel> filterModels = new ArrayList<>();

            for (int i = 0; i < filterList.size(); i++) {
                //validate
                if (filterList.get(i).getTitle().toUpperCase().contains(constraint)){
                    //add to filtered list
                    filterModels.add(filterList.get(i));
                }
            }
            results.count = filterModels.size();
            results.values = filterModels;

        }else {
            results.count = filterList.size();
            results.values = filterList;
        }
        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        //apply filter changes
        adapterPdfUser.pdfArrayList = (ArrayList<PdfModel>) results.values;

        //notify changes
        adapterPdfUser.notifyDataSetChanged();
    }
}
