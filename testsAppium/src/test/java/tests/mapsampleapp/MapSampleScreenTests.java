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

package tests.mapsampleapp;

import general.ErrorsManager;
import general.MobileDriverManager;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import screens.mapsampleapp.MapSampleScreen;
import screens.mapsampleapp.MapSampleHomeScreen;

public class MapSampleScreenTests extends MobileDriverManager {

    private MapSampleHomeScreen mapSampleHomeScreen;
    private MapSampleScreen mapSampleScreen;

    @BeforeMethod
    public void setAuthSampleLoginScreen() {
        mapSampleHomeScreen = new MapSampleHomeScreen(getDriver());
    }

    @Test
    public void FW_13_FW_14_verifyThatMapSampleAppOpensCorrectly() {
        assertTrue(mapSampleHomeScreen.verifyLoads(), basicErrorMsg("Unable to load the main screen"));
        assertTrue(mapSampleHomeScreen.printMainMapsElements(), basicErrorMsg("Unable to print the elements"));
        assertTrue(mapSampleHomeScreen.openMapSampleScreen(), basicErrorMsg("Maps screen can't be opened"));
        assertAll();
    }

    @Parameters({"deviceType"})
    private void validateDeviceTypeTesting(String deviceType) {
        mapSampleScreen = mapSampleHomeScreen.tapAndOpenMapSampleScreen();
        assertTrue(mapSampleScreen.verifyScreen(deviceType), basicErrorMsg("The maps screen was not loaded correctly"));
    }

    @Test
    public void FW_33_FW_34_verifyThatDeviceSupportSwipeGestures() {
     validateDeviceTypeTesting("");
     assertTrue(mapSampleScreen.swipeOnDeviceScreen(), basicErrorMsg("Unable to swipe on map screen"));
     assertAll();
    }

    @Test     @Parameters({"deviceType"})
    public void FW_53_verifyTappingMyLocationBtnMovesToUsersLocation(String deviceType) {
        validateDeviceTypeTesting("");
        assertTrue(mapSampleScreen.tapMyLocationBtn(deviceType), basicErrorMsg("Unable to tap My Location button (GPS) icon"));
        assertAll();
    }

    @Test    @Parameters({"deviceType"})
    public void FW_54_verifyNavigatingAndTappingMyLocationBtnMovesToUsersLocation(String deviceType) {
        validateDeviceTypeTesting("");
        assertTrue(mapSampleScreen.customXxSwipeOnScreen(1000, 100), basicErrorMsg("Unable to do a horizontal swipe"));
        assertTrue(mapSampleScreen.customYySwipeOnScreen(300, 1800), basicErrorMsg("Unable to do a vertical swipe"));
        assertTrue(mapSampleScreen.tapMyLocationBtn(deviceType), basicErrorMsg("Unable to tap My Location button (GPS) icon"));
        assertAll();
    }

    @Test
    public void FW_72_verifyThatPinGetsUpdatedLocationEachTimeMoves() {
        validateDeviceTypeTesting("");
        assertTrue(mapSampleScreen.pinMovesGetLoc(100, 1000), basicErrorMsg("Unable to swipe on map screen"));
        assertAll();
    }

    @Test
    public void FW_73_verifyThatPinLocationMatchesWhenShares() {
        validateDeviceTypeTesting("");
        assertTrue(mapSampleScreen.sharePinsLocation(), basicErrorMsg("Both locations do not match"));
        assertAll();
    }

    @Test
    public void FW_41_verifyThatDeviceCanSupportZoomGestures() {
        validateDeviceTypeTesting("");
        assertTrue(mapSampleScreen.doubleTapWithOneFingerXY(778, 1224), basicErrorMsg("The double tap can't be performed"));
        assertTrue(mapSampleScreen.singleTapWith2FingersXY(540, 1120), basicErrorMsg("The single tap with two fingers can't be performed"));
        assertTrue(mapSampleScreen.doubleTapWithOneFingerXYHoldAndSwipeUp(540, 1120), basicErrorMsg("The double tap, hold and swipe up can't be performed"));
        assertTrue(mapSampleScreen.doubleTapWithOneFingerXYHoldAndSwipeDown(540, 1120), basicErrorMsg("The double tap, hold and swipe down can't be performed"));
        assertAll();
    }








    @Test
    public void exceptionThrown() {
        try {
            validateAge(15);
        } catch (IllegalArgumentException e) {
            ErrorsManager.errNExpManager(e);
            System.err.println("Caught exception: " + e.getMessage());
        }
    }

    private static void validateAge(int age) {
        if (age < 18) {
            throw new IllegalArgumentException("Age must be at least 18");
        }
        System.out.println("Age is valid: " + age);
    }
}