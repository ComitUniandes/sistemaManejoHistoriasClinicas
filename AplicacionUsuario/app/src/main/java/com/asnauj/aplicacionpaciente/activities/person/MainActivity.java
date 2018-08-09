package com.asnauj.aplicacionpaciente.activities.person;

import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;

import com.asnauj.aplicacionpaciente.R;
import com.asnauj.aplicacionpaciente.entities.Paciente;
import com.asnauj.aplicacionpaciente.fragments.person.DoctorsFragment;
import com.asnauj.aplicacionpaciente.fragments.person.MedicFragment;
import com.asnauj.aplicacionpaciente.fragments.person.PersonFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements DoctorsFragment.OnFragmentInteractionListener,
        MedicFragment.OnFragmentInteractionListener, PersonFragment.OnFragmentInteractionListener{

    @BindView(R.id.activity_main_pager)
    ViewPager pager;
    @BindView(R.id.activity_main_tabs)
    TabLayout tabs;
    ViewPagerAdapter adapter;
    Paciente pas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        pas = (Paciente)getIntent().getSerializableExtra("PACIENTE");

        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new PersonFragment(), "");
        adapter.addFragment(new MedicFragment(), "");
        adapter.addFragment(new DoctorsFragment(), "");
        pager.setAdapter(adapter);
        tabs.setupWithViewPager(pager);
        tabs.getTabAt(0).setIcon(R.drawable.ic_person);
        tabs.getTabAt(1).setIcon(R.drawable.ic_file);
        tabs.getTabAt(2).setIcon(R.drawable.ic_lock);

    }

    public Paciente getPaciente(){
        return pas;
    }



    private class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
