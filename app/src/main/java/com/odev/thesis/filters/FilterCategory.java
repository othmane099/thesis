package com.odev.thesis.filters;

import android.widget.Filter;

import com.odev.thesis.adapters.AdapterCategory;
import com.odev.thesis.models.CategoryModel;

import java.util.ArrayList;

public class FilterCategory extends Filter {

    //arraylist in which we want to search
    ArrayList<CategoryModel> filterList;
    //adapter in which filter need to be implemented
    AdapterCategory adapterCategory;

    public FilterCategory(ArrayList<CategoryModel> filterList, AdapterCategory adapterCategory) {
        this.filterList = filterList;
        this.adapterCategory = adapterCategory;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results = new FilterResults();
        //value should not be null and empty
        if (constraint != null && constraint.length() > 0){
            //change to upper case to avoid case sensitivity
            constraint = constraint.toString().toUpperCase();
            ArrayList<CategoryModel> filterModels = new ArrayList<>();

            for (int i = 0; i < filterList.size(); i++) {
                //validate
                if (filterList.get(i).getCategory().toUpperCase().contains(constraint)){
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
        adapterCategory.modelCategories = (ArrayList<CategoryModel>) results.values;

        //notify changes
        adapterCategory.notifyDataSetChanged();
    }
}
