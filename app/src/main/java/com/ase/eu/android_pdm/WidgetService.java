package com.ase.eu.android_pdm;

import android.content.Intent;
import android.widget.RemoteViewsService;


public class WidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new TraseuWidgetFactory(getApplicationContext());
    }
}