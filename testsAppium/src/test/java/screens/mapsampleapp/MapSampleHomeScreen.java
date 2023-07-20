/*
 * Copyright 2023 Open Mobile Hub
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package screens.mapsampleapp;

import general.BaseScreen;
import general.ErrorsManager;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class MapSampleHomeScreen extends BaseScreen {

    public MapSampleHomeScreen(AndroidDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @Override
    public boolean verifyLoads() {
        return waitForMobElementToBeVisible(topActionBar) && waitForMobElementToBeVisible(openMapBtn);
    }

    /*
    UI MAPS ELEMENTS
     */

    @AndroidFindBy(id="com.omh.android.maps.sample:id/toolbar")
    private WebElement topActionBar;

    @AndroidFindBy(className="android.widget.TextView")
    private WebElement topActionBarTxt;

    @AndroidFindBy(id="com.omh.android.maps.sample:id/button_open_map")
    private WebElement openMapBtn;



    /*
    Methods
     */

    public boolean printMainMapsElements() {
        boolean flag = true;

        try {
            System.out.println(getTextFromMobElement(topActionBarTxt));
            System.out.println(getTextFromMobElement(openMapBtn));
            flag = true;
        } catch (Exception e) {ErrorsManager.errNExpManager(e);}

        return flag;
    }

    public boolean openMapSampleScreen(){
        return tapMobElement(openMapBtn) && implicityWaitTimeOnScreen();
    }

    /*
     RETURN-REDIRECT SCREEN CALLS
    */
    public MapSampleScreen tapAndOpenMapSampleScreen() {
        if(openMapSampleScreen()) {
            return new MapSampleScreen(this.driver);
        } else {return null;}
    }

}
