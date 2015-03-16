package com.example.william.moneycontrol.Configuraciones;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.dropbox.sync.android.DbxAccount;
import com.dropbox.sync.android.DbxAccountManager;
import com.dropbox.sync.android.DbxDatastore;
import com.dropbox.sync.android.DbxDatastoreManager;
import com.dropbox.sync.android.DbxException;
import com.example.william.moneycontrol.R;


public class ConfigFragment extends Fragment {

    private View rootView;
    public ConfigFragment() {
        // Required empty public constructor
    }

    static final int REQUEST_LINK_TO_DBX = 0;  // This value is up to you

    private Button mLinkButton;
    private DbxDatastoreManager mDatastoreManager;
    private DbxAccountManager mAccountManager;
    private String APP_SECRET = "17o08311yppjfur";
    private String APP_KEY = "ugps1msaywg3kjm";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_config, container, false);

        mAccountManager = DbxAccountManager.getInstance(getActivity().getApplicationContext(), APP_KEY, APP_SECRET);

        // Button to link to Dropbox
        mLinkButton = (Button) rootView.findViewById(R.id.link_button);
        mLinkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAccountManager.startLink(getActivity(), REQUEST_LINK_TO_DBX);
            }
        });

        // Set up the datastore manager
        if (mAccountManager.hasLinkedAccount()) {
            try {
                // Use Dropbox datastores
                mDatastoreManager = DbxDatastoreManager.forAccount(mAccountManager.getLinkedAccount());
                // Hide link button
                mLinkButton.setVisibility(View.GONE);
            } catch (DbxException.Unauthorized e) {
                System.out.println("Account was unlinked remotely");
            }
        }
        if (mDatastoreManager == null) {
            // Account isn't linked yet, use local datastores
            mDatastoreManager = DbxDatastoreManager.localManager(mAccountManager);
            // Show link button
            mLinkButton.setVisibility(View.VISIBLE);
        }

        return rootView;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_LINK_TO_DBX) {
            if (resultCode == Activity.RESULT_OK) {
                DbxAccount account = mAccountManager.getLinkedAccount();
                try {
                    // Migrate any local datastores to the linked account
                    mDatastoreManager.migrateToAccount(account);
                    // Now use Dropbox datastores
                    mDatastoreManager = DbxDatastoreManager.forAccount(account);
                    // Hide link button
                    mLinkButton.setVisibility(View.GONE);
                } catch (DbxException e) {
                    e.printStackTrace();
                }
            } else {
                // Link failed or was cancelled by the user
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

}
