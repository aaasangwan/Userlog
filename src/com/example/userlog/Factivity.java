package com.example.userlog;

import java.util.Arrays;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.model.GraphUser;

public class Factivity extends Activity {
	
String strEmail;
String strFirstName;
String strLocation;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_factivity);
	
	  }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.factivity, menu);
		doSocialNetworkinWithFacebook();
		return true;
	}

	@SuppressWarnings("deprecation")
	private void doSocialNetworkinWithFacebook()
    {
        // check for session 
         Session session=Session.getActiveSession();
         if (session != null && session.isOpened()) 
             {  
                // user is already login show
                    try
                        {
                            Session.OpenRequest request = new Session.OpenRequest(this);
                            request.setPermissions(Arrays.asList("email", "publish_actions"));
                        }
                    catch (Exception e)
                        {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                 Request.executeMeRequestAsync(session, new Request.GraphUserCallback() 
                        {
                          // callback after Graph API response with user object

                          @Override
                          public void onCompleted(GraphUser user, Response response) 
                          {
                              if (user != null) 
                               {
                                 //  Toast.makeText(activity, "Welcome  "+user.getName(), Toast.LENGTH_SHORT).show();
                                  // publishFeedDialog(session);
                                    try
                                        {
                                            strFirstName = user.getFirstName().toString();
                                            strLocation = user.getLocation().getProperty("name").toString();
                                            strEmail = user.asMap().get("email").toString();

                                        }
                                    catch (Exception e)
                                        {
                                            // TODO Auto-generated catch block
                                            e.printStackTrace();
                                            strEmail="";
                                        }
                                    TextView welcome = (TextView) findViewById(R.id.welcome);
                                    welcome.setText(strEmail);
                                }
                          }
                    });

             }
         else
             {
                 // user is not log in 
                 //show  login screen

                // start Facebook Login

                    try
                        {
                            Session.OpenRequest request = new Session.OpenRequest(this);
                            request.setPermissions(Arrays.asList("email", "publish_actions"));
                        }
                    catch (Exception e)
                        {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                 Session.openActiveSession(this, true, new Session.StatusCallback() 
                 {

                     // callback when session changes state
                    public void call(final Session session, SessionState state, Exception exception) 
                    {
                        //session.openForRead(new Session.OpenRequest(this).setPermissions(Arrays.asList("email")));
             

                        if (session.isOpened()) 
                        {                               
                            // make request to the /me API

                            Request.executeMeRequestAsync(session, new Request.GraphUserCallback()

                                {
                                  // callback after Graph API response with user object
                                  @Override
                                  public void onCompleted(GraphUser user, Response response) 
                                  {
                                      if (user != null) 
                                       {

                                           //Toast.makeText(activity, "Welcome  "+user.getName(), Toast.LENGTH_SHORT).show();
                                          // publishFeedDialog(session);
                                           try
                                            {
                                                    strFirstName = user.getFirstName().toString();
                                                    strLocation = user.getLocation().getProperty("name").toString();
                                                    strEmail = user.asMap().get("email").toString();
                                            }
                                        catch (Exception e)
                                            {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                                strEmail="";
                                            }

                                           TextView welcome = (TextView) findViewById(R.id.welcome);
                                           welcome.setText(strEmail);
                                        }
                                  }
                            });

                        }
                        

                    }
                  });
             }

    }
    

    }






