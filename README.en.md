# CineMates
*CineMates20* is a complex, distributed system aimed at offering a modern, multi-platform *social network* for film enthusiasts.

The system consists of a secure, performant and scalable back-end, and two Clients (one on desktop platform, and one on mobile) through which users can enjoy the functionality of the system in an intuitive, fast and pleasant way.

## Features to be implemented
- Registration of users. The ability to authenticate using accounts on other platforms such as Google or Facebook is appreciated.
- [ ] Search for movies, including using APIs offered by external services such as The Movie DataBase (TMDB) or Open Movie DataBase (OMDB).
- [ ] Add or remove movies from your list of favorites, or movies to watch;
- [ ] Post a review of a movie you've seen, including a rating and text description.
- [ ] Create custom movie lists, including a title and description.
- [ ] Send/receive link requests from other users.
- [ ] View a friend's profile, which shows his or her most recent activities (e.g.: posted reviews, added movies to a list, created lists, left comments).
- [ ] View favorite movies you have in common with a particular friend.
- [ ] Display recent actions taken by friends in a feed. Examples of recent actions to display include: adding a friend, adding a movie to a list, creating a custom list, reviewing a movie, etc.
- [ ] Random choice of a movie from the list of movies to watch, to help undecided users. If the user is not satisfied with the randomly selected movie, they can request a new random selection. The System must ensure that the same movie is never suggested twice in a row.
- [ ] Ability to view, comment, and rate your friends' personalized movie lists. You can also express quick opinions (e.g., "like," "dislike," etc.).
- [ ] Ability to view, comment on, and rate reviews posted by friends. It is also possible to express quick opinions (e.g. "I like it", "I don't like it", etc.).           Reviews and/or comments that receive at least three spoiler alerts are blacked out by default, and users can only view them by pressing a special button.
- [ ] Ability to recommend a favorite movie or a custom list to friends (who will be notified of the recommendation).

### Feature specifications
*CineMates20* allows users to discover new movies, and share their movie interests with friends. An authenticated user can view in a feed the recent actions of their connections, in chronological order. In addition, a user can also search for movies. The ability to search by title is absolutely essential. The ability to perform advanced searches by year of release \ genre \ actors \ directors is welcome. Once a movie is found, the user can review it, add it to their list of favorites or movies to watch, or even add it to a custom list. Obviously, a user will be able to view the list of their own lists and reviews.

A user can search among users for their friends, and send a connection request, which will be notified to the friend and can be accepted or rejected by the latter. In addition, a user can view both the profile and the reviews \ personalized lists of their friends, comment on them and possibly indicate a quick reaction ("like", "dislike", "angry", "applaud", etc.).

A user can also suggest their own review \ custom list to one or more friends, who will receive a notification. To limit unwanted notifications, it must not be possible to recommend the same thing to the same friend multiple times.

It must be possible for users to report content (comments, reviews, etc.) with spoilers and/or inappropriate content (e.g. insults, threats, etc.). Content that receives at least three spoiler alerts should be blacked out by default, leaving the user the option to explicitly request to view it. Content with at least three reports of inappropriateness will be blacked out by default until the administrator decides.

Finally, administrators can view real-time statistics about the system (number of users, number of searches, number of lists, number of reviews, etc.), recommend a particular movie to all users, send promotional emails to all users, and decide on the fate of user-reported content.

## Enhancements
- [x] Use ViewBinding
- [ ] Use DataBinding
- [x] Improve navigation
- [ ] Improve refresh of fragment lists
- [ ] Improve profile management
- [ ] Increase customization
- [x] Improve UX
- [x] Better manage credentials 
- [ ] Secure backend with Azure/AWS
