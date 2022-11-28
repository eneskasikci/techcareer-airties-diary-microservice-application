# Introduction

This is the Diary Application part of the Techcareer / Airties Cloud SW Bootcamp Graduation projects.

Using Rest API, Spring Boot, H2 Database, Docker.

# Docker Setup

Start the terminal in the project directory. Start the containers by "docker-compose up".


## Mappings
### POST Mappings for the Diary Application
#### createDiaryPost: Creates a diary post in JSON format

- http://localhost:5555/api/diaryApp/posts/createDiaryPost

        {      
            "request_diaryTitle": "post6123",
            "request_diaryContent": "post1236",
            "request_diaryUserName" : "eneskasikci"
            "request_diaryUserId" : 1 -- This is only required if its sent from the Register Login Application.
        }

Creates a new Diary Post in the database with a Post Request parameters. If the user does not exist, it will be created.

### GET Mappings
#### getAllPosts: List every product that is on the database.
- http://localhost:5555/api/diaryApp/posts/getAllPosts
- http://localhost:5555/api/diaryApp/posts/getAllPosts?userId=X (X is the user id)
 
Used for getting the every post in the diary database. It shouldn't be accessible because diaries are personal. 
But this is here for the testing purposes. If you want to see the posts of a specific user, you can pass the userId parameter.

#### Listing posts by the given parameters.
#### To get the specific posts, you can use the parameters below.
- Lists posts from the user.
    - http://localhost:5555/api/diaryApp/posts/getAllPostsFromUser/ {username}
        
        Ex. http://localhost:5555/api/diaryApp/posts/getAllPostsFromUser/eneskasikci

      To see diary posts from the user with the given username. We are passing the username as a path variable. 
      If the user doesn't exist, it will return null.
      This can be limited to the user itself with controls from front-end.


#### To see a specific post in the diary database.
- http://localhost:5555/api/diaryApp/posts/{postId}

  Ex. http://localhost:5555/api/diaryApp/posts/1

We are passing the postId. If the post doesn't exist, it will return null.

### Delete Mappings
For deletion, you can use the parameters below.

#### To delete a specific post in the diary database of the given username.
- http://localhost:5555/api/diaryApp/posts/deletePostIfUserIsOwner

        {
            "deletionrequest_userName" : "eneskasikci",
            "deletionrequest_diaryId" : 1
        }


   We are passing the delete request with the JSON body. If the username has a post with the given postId, it will delete the post.
   If the given username trying to delete a post that is not his, or the there are no post with the given postId, it will return a message accordingly.

#### To delete all the posts in the database.
- http://localhost:5555/api/diaryApp/posts/deletePosts

   This will delete every post in the database. Here for the testing purposes.

### Put Mappings
#### Update requests for the posts.
#### To update a specific post in the diary database.
- http://localhost:5555/api/diaryApp/posts/updatePost/{postId}
- http://localhost:5555/api/diaryApp/posts/updatePost/{postId}?userId=X (X is the user id)

        {
            "request_diaryUpdatedPostTitle": "updatedTitle",
            "request_diaryUpdatedPostContent": "updatedContent"",
            "request_diaryUpdatedPost_userName" : "eneskasikci"
        }

   We are passing the update request with the JSON body. If the username has a post with the given postId, it will update the post.
   If the given username trying to update a post that is not his, or the there are no post with the given postId, it will return a message accordingly.

#### To update post with the username.
- http://localhost:5555/api/diaryApp/posts/updatePostIfUserIsOwner
    
        {
            "request_diaryUpdatedPostTitle": "updatedTitle",
            "request_diaryUpdatedPostContent": "updatedContent"",
            "request_diaryUpdatedPost_userName" : "eneskasikci",
            "request_diaryUpdatedPostId" : 1
        }

  This will update the post with the given username, and the postID. Made for the Register Login Gateway.

#### Swagger
-Implemented Swagger Documentation. To see the documentation head over to the http://localhost:5555/swagger-ui/index.html#/
