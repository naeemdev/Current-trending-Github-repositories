current trending Github repositories

Requirements

        ● The app should support a minimum SDK version of 19
        ● The app should fetch the trending repositories from the provided public API and display it to
        the users 
        ● While the data is being fetched, the app should show a loading state (refer fig. 2). Shimmer
        animation is optional
        ● If the app is not able to fetch the data, then it should show an error state to the user with an
        option to retry again 
        ● All the items in the list should be in their collapsed state by default and can be expanded on
        being tapped 
        ● Tapping any item will expand it to show more details and collapse any already expanded item.
        Tapping the same item in expanded state should collapse it
        ● The app should be able to handle configuration changes (like rotation)
        ● The app should have 100% offline support. Once the data is fetched successfully from remote,
        it should be stored locally and served from cache thereafter till the cache is not expired
        ● The cached data should only be valid for a duration of 2 hour after that the app should attempt
        to refresh the data from remote and purge the cache if successful
        ● The app should give a pull-to-refresh option to the user to force fetch data from remote 
