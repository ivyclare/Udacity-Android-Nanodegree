package com.ivy.bakingapp.widget;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;

public class UpdateBakingService extends IntentService {

    public static String FROM_INGRIEDIENTS_ADAPTER ="FROM_INGRIEDIENTS_ADAPTER";

    public UpdateBakingService() {
        super("UpdateBakingService");
    }

    public static void startBakingService(Context context,ArrayList<String> fromActivityIngredientsList) {
        Intent intent = new Intent(context, UpdateBakingService.class);
        intent.putExtra(FROM_INGRIEDIENTS_ADAPTER,fromActivityIngredientsList);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            ArrayList<String> fromActivityIngredientsList = intent.getExtras().getStringArrayList(FROM_INGRIEDIENTS_ADAPTER);
            handleActionUpdateBakingWidgets(fromActivityIngredientsList);

        }
    }

    private void handleActionUpdateBakingWidgets(ArrayList<String> fromActivityIngredientsList) {
            Intent intent = new Intent("android.appwidget.action.APPWIDGET_UPDATE2");
            intent.setAction("android.appwidget.action.APPWIDGET_UPDATE2");
            intent.putExtra(FROM_INGRIEDIENTS_ADAPTER,fromActivityIngredientsList);
            sendBroadcast(intent);
       }

    }
