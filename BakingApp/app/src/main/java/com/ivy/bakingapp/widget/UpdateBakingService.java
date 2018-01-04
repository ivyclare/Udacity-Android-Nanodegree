
/*
 * Copyright 2017 Nikos Vaggalis
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ivy.bakingapp.widget;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;

/**
 * Project "Baking App" - Created by Nikos Vaggalis as part of the Udacity Android Developer Nanodegree on 13/6/2017.
 */

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
