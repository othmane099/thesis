package com.odev.thesis.filters;

import android.widget.Filter;

import com.odev.thesis.adapters.AdapterPdfAdmin;
import com.odev.thesis.models.PdfModel;

import java.util.ArrayList;

public class FilterPdfAdmin extends Filter {

    //arraylist in which we want to search
    ArrayList<PdfModel> filterList;
    //adapter in which filter need to be implemented
    AdapterPdfAdmin adapterPdfAdmin;

    public FilterPdfAdmin(ArrayList<PdfModel> filterList, AdapterPdfAdmin adapterPdfAdmin) {
        this.filterList = filterList;
        this.adapterPdfAdmin = adapterPdfAdmin;
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
        adapterPdfAdmin.pdfArrayList = (ArrayList<PdfModel>) results.values;

        //notify changes
        adapterPdfAdmin.notifyDataSetChanged();
    }
}
