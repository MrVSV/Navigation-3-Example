# Android Navigation3 Example

## Required behavior
* When you launch the app in portrait mode, you should see a screen with a list of squares ✔
* When you launch the app in landscape mode on a tablet, you should see a screen with a list of squares and a screen with a circle
* Each tab should store its own state ✔
* Clicking on an open tab should reset its state
* If the tab is not the home tab and its backstack contains only the start screen, then pressing the back button should switch the tab to the home tab and display its saved state ✔

## Known issues
* ~~The Home tab does not store its state~~
* Clicking on an open tab does not reset its state
* When transitioning from one square to another, the transition animation applies to the entire scene, not just the screen containing the square

## Desirable improvements
* Opt out of ListDetailPaneScaffold ✔
* Transitions between screens of different backstacks
