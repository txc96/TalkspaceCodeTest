# TalkspaceCodeTest
Android app for TalkSpace Mobile Assignment

##TODO
- Filter articles better
- Vertical/Horizontal view for article

##Architecture

The application is split into a few packages:
- Activity
  - Models
  - UI
  - Activity
- Networking
  - Models
  - Services

Activity handles most of the logic of the application, both data and UI wise. This is where the responses
are sent and parsed, local objects are created and sent to the adapter, local saving and retrieving is 
handled, and any other code relevant to the application. 

The models section contains a custom object `ArticleObject` for parsing the network response, that is 
lighter on fields than the corresponding model in the networking package. It also has a model for the 
filter object, used to add or remove a category from the request. 

The UI folder contains adapters for both the spinner and recycler view. Mapping the data to the views 
correctly and calling the main activity with a callback when some logic needs to be handled. (typically 
with the request or an intent).

Networking is reserved for retrofit code, mainly the API Service singleton, interface for the request method,
and the models from the request.

There is a model for each object received in the response from the response as a whole to a keyword on an 
article. That way, retrofit can easily map the response to objects, which can simply have the relevant `.get()` 
called for any data that might be in the response.

##Reasoning

- Why Java? While I'm comfortable in kotlin for more basic things, some of the advanced stuff like Retrofit
  and adapters I am stronger with in Java
- Why retrofit? Because it's what I was the most familiar with
- Why separate out the networking to it's own package? To keep it clean and separate from the rest of the app,
  as well as isolating it so it won't be affected by any changes to the activity side of things
- Why create all those models? To be thorough
- Why create a new model for an article when you made one in networking? I felt it simplified the adapter process
  by only having the fields we needed on the object, and having an object to work with that had significantly
  less fields would be a bit more data efficient at the start.
- Why just a main activity and no fragments? I started from an empty project, so I didn't want to pile too much
  work onto this, plus with only one screen I didn't feel it was necessary
- Why use callbacks to the activity in the adapters? It keeps the ui code free from as much non necessary
  logic as possible, cutting down on performance slowing and dropped frames
- Other questions? Please feel free to ask me

##Missing features/trde-offs

- The light/dark theme is a bit of a cop out, I wanted to hit the goal but with the shortened time
  frame setting up custom themes didn't feasible with the scope/time.
- Right now saving local articles is just saving the article objects. So the title, 
  abstract, author, etc. It doesn't actually save an article for offline viewing, I would have liked
  to have found a way where I could download the webpage and pop that open on the click for saved articles
  but that was outside time/scope. There should also be a visual sign an article is downloaded, and probably
  a viewing fragment.
- Filtering works pretty well, but can be a bit janky sometimes. A better solution might exist with independent
  buttons that doesn't rely on a custom adapter.
- I would have like to implement an infinite scrolling form of loading over page buttons, but again scope/time
- I would have like to have used the `viewbinding` library to utilize an MVVM approach, but scope/time. Also I 
  didn't want to start with an project template that already had it, felt that would have been cheap. In the 
  real world that would bea no brainer, to already have it set up.
- I do not know compose all that well, but if possible I would have like dto have used that over XML.