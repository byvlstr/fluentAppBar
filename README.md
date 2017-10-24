# Fluent App Bar

<img src="https://github.com/byvlstr/fluentAppBar/blob/master/assets/fluentappbar.png" width=150 align=right>



Fluent App Bar is an Android "bottom-sheet" enabling a whole new bottom navigation and menu experience

* **Innovative:** Fluent App Bar is an innovative UI/UX to manage (Fragment) navigation and visualize menu options.
* **Beautiful UI:** An elegant UI with a very smooth UX. A bottom navigation bar and a menu in one UI widget. 
* **Inspired by:** Microsoft's [Fluent Design System](https://fluent.microsoft.com/).
* **Many thanks to:** Dmitry Saviuk who made [BlurView](https://github.com/Dimezis/BlurView), already used for the [Blur Dialog](https://github.com/byvlstr/blurdialog)

<img src="https://github.com/byvlstr/fluentAppBar/blob/master/assets/fluentappbar.gif" width=350>

# Usage



Add this XML snippet to your layout.

```xml


<com.vlstr.fluentappbar.FluentAppBar
        android:id="@+id/fluent_app_bar"
        android:layout_width="match_parent"

        android:layout_height="wrap_content"

        app:fluent_background_colour="@color/colorPrimary"
        app:fluent_foreground_colour="#FFFFFF"

        app:fluent_app_bar_type="FULL_FLUENT"
        app:layout_behavior="android.support.design.widget.BottomSheetBehavior">

</com.vlstr.fluentappbar.FluentAppBar>



```


And integrate the FluentAppBar this way:

```java
MainActivity.java
---

private void setupFluentAppBar() {
        fluentAppBar = (FluentAppBar) findViewById(R.id.fluent_app_bar);
        fluentAppBar.setNavigationMenu(R.menu.fluent_app_bar_main_menu, this);
        fluentAppBar.setSecondaryMenu(R.menu.fluent_app_bar_secondary_menu, this);
        fluentAppBar.setBlurRadius(10);
}
```

And to handle the navigation or secondary menu item click:

```java
MainActivity.java
---

@Override
public void onClick(View v) {
    int resId = (int) v.getTag();
    switch (resId){
        //Navigation Menu
        case R.id.nav_all:
            fluentAppBar.collapse();
            break;
        case R.id.nav_album:
            break;
        case R.id.nav_keywords:
            break;

        // Secondary Menu
        case R.id.menu_sync:
            fluentAppBar.collapse();
            break;
        case R.id.menu_assistant:
            break;
        case R.id.menu_shared:
            break;
    }
  }
```

You will have to provide 2 XML Menu resource files for the navigation menu and the secondary menu.

```xml


<menu xmlns:android="http://schemas.android.com/apk/res/android">
    <item
        android:icon="@drawable/ic_all"
        android:id="@+id/nav_all"
        android:title="All" />

    <item
        android:icon="@drawable/ic_album"
        android:id="@+id/nav_album"
        android:title="Albums" />

    <item
        android:icon="@drawable/ic_keywords"
        android:id="@+id/nav_keywords"
        android:title="Keywords" />
</menu>

<menu xmlns:android="http://schemas.android.com/apk/res/android">
    <item
        android:icon="@drawable/ic_sync"
        android:id="@+id/menu_sync"
        android:title="Show Sync Status" />

    <item
        android:icon="@drawable/ic_assistant"
        android:id="@+id/menu_assistant"
        android:title="Photo Assistant" />

    <item
        android:icon="@drawable/ic_shared"
        android:id="@+id/menu_shared"
        android:title="Shared Photos" />
</menu>
```

# Compatibility
This Android library is currently supported by devices with API > 17.


# Customisation

Each of the following has got getter/setter methods. Also, the type, background colour and foreground colour can be directly set in the XML layout file.
* Navigation Menu: provide the icons and title for the navigation menu
* Secondary menu
* Type: 3 types are available to you
  - FULL_FLUENT: the fluent blur is always enabled
  - CLICK_FLUENT: the blur is enabled when the FluentAppBar is expanded
  - DISABLE_FLUENT: no blur effect is being used

* Background colour: specify the FluentAppBar's background colour
  - Used as is for the DISABLE_FLUENT and CLICK_FLUENT types
  - Made transparent according to the provided options for FULL_FLUENT and CLICK_FLUENT (when expanded) => works like a filter colour
* Foreground colour: specify the text colour and icon tint
* Ripple style: specify whether you want to use the custom "Fluent Ripple" or keep the default Android ripple

* Background alpha: Set how strong the alpha should be when making the backgrond colour a transparent filter
* Blur radius: Range [1-25] - The lower it is, the more you will see the FluentAppBar's background.

Additional customization
These 3 customization options are done through overriding the "@dimen" resource name in your own dimens.xml file. Please name the dimensions like follows:
* Text size
  - Navigation menu ("fluentappbar_navigation_text_size")
  - Secondary menu ("fluentappbar_secondary_menu_text_size")
* Icon size ("fluentappbar_icon_height")

Additional methods
* collapse(): collapse the FluentAppBar after a short delay of 500ms (in order to see the ripple)
* collapseWithoutDelay(): collapse the FluentAppBar without the delay
* expand()


# Gradle


```groovy
dependencies {
  compile 'com.vlstr:fluentappbar:1.1.0'
}
```

For Maven:

```xml
<dependency> 
  <groupId>com.vlstr</groupId> 
  <artifactId>fluentappbar</artifactId> 
  <version>1.1.0</version> 
  <type>pom</type> 
</dependency>
```


# Examples

<img src="https://github.com/byvlstr/fluentAppBar/blob/master/assets/fluent_bar.png" width=350>
<img src="https://github.com/byvlstr/fluentAppBar/blob/master/assets/disable_or_click_fluent.png" width=350 align=right>
<img src="https://github.com/byvlstr/fluentAppBar/blob/master/assets/disable_fluent.png" width=350>


# Logs

### 1.1.0

- "Override" customization for text and icon sizes
- Add type "Click Fluent"
- Improve touch interaction with the Fluent App Bar sliding 

### 1.0.0

- Initial version


# Used by/in

Feel free to contact me to have your project and name stated here

# Credits

Creator: Valentin Lungenstrass [http://www.vlstr.com/](http://vlstr.com/) | contact [at] vlstr [dot] com

<a href="https://twitter.com/byvlstr">
  <img alt="Follow me on Twitter"
       src="https://raw.githubusercontent.com/florent37/DaVinci/master/mobile/src/main/res/drawable-hdpi/twitter.png" />
</a>
<a href="https://www.linkedin.com/in/valentin-lungenstrass-3a496b97/">
  <img alt="Follow me on LinkedIn"
       src="https://raw.githubusercontent.com/florent37/DaVinci/master/mobile/src/main/res/drawable-hdpi/linkedin.png" />
</a>


License
--------

    Copyright 2017 Valentin Lungenstrass.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
