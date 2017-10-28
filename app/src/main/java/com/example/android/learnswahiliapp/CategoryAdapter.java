package com.example.android.learnswahiliapp;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by kibo on 9/23/17.
 */

public class CategoryAdapter extends FragmentPagerAdapter {

private Context mContext;


    public CategoryAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }


    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new NumbersFragment();
        } else if (position == 1) {
            return new GreetingsFragment();
        } else if (position == 2) {
            return new PeopleFragment();
        }else if(position == 3){
            return  new PronounsFragment();
        }else if(position == 4){
            return new PhrasesFragment();
        } else
            return new ProvebsFragment();
    }

    @Override
    public int getCount() {
        return 6;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(position == 0){
            return mContext.getString(R.string.category_numbers);
        }else if(position==1){
            return mContext.getString(R.string.category_colors);
        }else if(position==2){
            return mContext.getString(R.string.category_people);
        }else if (position==3){
           return mContext.getString(R.string.category_prefix);
        }
        else if (position==4){
            return mContext.getString(R.string.category_phrases);
        }
        else {
            return mContext.getString(R.string.category_provebs);
        }
    }
}
