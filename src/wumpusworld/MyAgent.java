package wumpusworld;

/**
 * Contains starting code for creating your own Wumpus World agent.
 * Currently the agent only make a random decision each turn.
 * 
 * @author Johan Hagelbäck
 */
public class MyAgent implements Agent
{
    private World w;
    int rnd;
    int counter =0;
    int c_pit1=0;
    int counter2 =0;
    int counter3 =0;
    int counter3_1 =0;
    int c_top=0;
    int c_right=0;
    int c_bottom=0;
    int c_left=0;
    int c_stench=0;
    int c_B1=0;
    int c_B1_1=0;
    int c_B2=0;
    int c_B2_1=0;
    int c_B3=0;
    int c_B3_1=0;
    int c_B4=0;
    int c_B4_1=0;
    int move=0;
    int c_else=0;
    
    /**
     * Creates a new instance of your solver agent.
     * 
     * @param world Current world state 
     */
    public MyAgent(World world)
    {
        w = world;   
    }
   
            
    /**
     * Asks your solver agent to execute an action.
     */

    public void doAction()
    {
        move=0;
        System.out.println("-----------------------------------------------------------------------------------------------------");
        System.out.println("-----------------------------------------------------------------------------------------------------");
        System.out.println("counter="+counter+" #at the main function");
        System.out.println("move value="+move);
        //Location of the player
        int cX = w.getPlayerX();
        int cY = w.getPlayerY();
        
        System.out.println("position of bot before moving ("+cX+","+ cY+")");
        
        //Basic action:
        //Grab Gold if we can.
        if (w.hasGlitter(cX, cY))
        {
            w.doAction(World.A_GRAB);
            return;
        }
        
        //Basic action:
        //We are in a pit. Climb up.
        if (w.isInPit())
        {
            w.doAction(World.A_CLIMB);
            return;
        }
        
        //Test the environment
        if (w.hasBreeze(cX, cY))
        {
            System.out.println("I am in a Breeze");
        }
        if (w.hasStench(cX, cY))
        {
            System.out.println("I am in a Stench");
        }
        if (w.hasPit(cX, cY))
        {
            System.out.println("I am in a Pit");
        }
        if (w.getDirection() == World.DIR_RIGHT)
        {
            System.out.println("I am facing Right");
        }
        if (w.getDirection() == World.DIR_LEFT)
        {
            System.out.println("I am facing Left");
        }
        if (w.getDirection() == World.DIR_UP)
        {
            System.out.println("I am facing Up");
        }
        if (w.getDirection() == World.DIR_DOWN)
        {
            System.out.println("I am facing Down");
        }
       
// AVOID COLLIDING THE WALLS-------------------------------------------------------------------------------------------------------------------------------------------    
         if (!w.isValidPosition(cX, cY-1) && (w.getDirection() == World.DIR_DOWN)) // bottom wall
           { 
             c_bottom+=1;
               System.out.println("c_bottom value is= "+c_bottom);
               if(c_bottom >=2)
               {
                   c_bottom=0;
                   w.doAction(World.A_TURN_RIGHT);
               
               }
               
               else
               {
                  if (!w.isValidPosition(cX+1, cY)) // right bottom corner
                  {
                      System.out.println("1:right bottom corner");
                      w.doAction(World.A_TURN_LEFT);
                  }
                    System.out.println("2:bottom wall ");
                    w.doAction(World.A_TURN_LEFT); // moving direction away from wall
                }
               
           }
    else if (!w.isValidPosition(cX+1, cY) && (w.getDirection() == World.DIR_RIGHT)) // right wall
           {
           c_right+=1;
              System.out.println("c_right value is= " +c_right);
              
              if(c_right >=3)
              {
                  c_right=0;
                  w.doAction(World.A_TURN_RIGHT);
              }
              else
              { 
              
                    if (!w.isValidPosition(cX, cY+1)) //right top corner
                  {
                      System.out.println("5: right top corner");
                      w.doAction(World.A_TURN_LEFT);
                  }
                   System.out.println("6: right wall");
                    w.doAction(World.A_TURN_LEFT); // moving direction away from wall
                   
               
           }
              
           }
   else if (!w.isValidPosition(cX, cY+1) && (w.getDirection() == World.DIR_UP)) //top wall
           { 
             c_top+=1;
             System.out.println("c_top value is= "+c_top);
             
             if(c_top >= 2)
             {
                 c_top=0;
               w.doAction(World.A_TURN_RIGHT);
             }
             
             else{  
                    if (!w.isValidPosition(cX-1, cY)) // left top corner
                  {
                      System.out.println("3: left top corner");
                      w.doAction(World.A_TURN_LEFT);
                  }
                    System.out.println("4: top wall");
                    w.doAction(World.A_TURN_LEFT); // moving direction away from wall
               
             }
           }
        
   else  if (!w.isValidPosition(cX-1, cY) && ( w.getDirection() == World.DIR_LEFT)) //left wall
           { 
                c_left+=1;
                System.out.println("c_left value is= "+c_left);
                
                if(c_left >=2)
                {
                    c_left=0;
                    w.doAction(World.A_TURN_RIGHT);
                }
                else
                        {
                    if (!w.isValidPosition(cX, cY-1)) // bottom left corner
                  {
                      System.out.println("7: bottom left corner");
                      w.doAction(World.A_TURN_LEFT);
                  }
                    System.out.println("8: left wall");
                    w.doAction(World.A_TURN_LEFT); // moving direction away from wall
                        }
           }
// END ## DONE WITH COLLIDING ##-------------------------xxxxx--------------------------xxxxx--------------------xxxxx--------------------------------------------------------------

//STENCH AND BREEZE---------------------------------------------------------------------------------------------------------------------------------------------------------------   
    if(w.hasStench(cX, cY) && w.hasBreeze(cX, cY) && move==0)
    {
      
        counter2+=1;
        System.out.print(" STENCH & BREEZE counter2="+counter2);
        
        if(w.hasStench(cX-1, cY-1) && w.hasArrow())
        {
            move=1;
            System.out.println("CASE 1:hasStench(cX-1, cY-1) && w.hasBreeze(cX-1, cY-1)");
           if(w.hasArrow())
           {
            if(w.isVisited(cX-1, cY))
            {
                System.out.println("if");
                if(w.getDirection() == World.DIR_DOWN)
                    w.doAction(World.A_SHOOT);
                else if(w.getDirection() == World.DIR_LEFT)
                {
                    w.doAction(World.A_TURN_LEFT);
                    w.doAction(World.A_SHOOT);
                }
                else if(w.getDirection() == World.DIR_RIGHT)
                {
                    w.doAction(World.A_TURN_RIGHT);
                    w.doAction(World.A_SHOOT);
                }
                else if(w.getDirection() == World.DIR_UP)
                {
                    w.doAction(World.A_TURN_LEFT);
                    w.doAction(World.A_TURN_LEFT);
                    w.doAction(World.A_SHOOT);
                }
            }
            else 
            {
                System.out.println("else");
                if(w.getDirection() == World.DIR_DOWN)
                {
                     w.doAction(World.A_TURN_RIGHT);
                    w.doAction(World.A_SHOOT);
                }
                else if(w.getDirection() == World.DIR_LEFT)
                {
                   
                    w.doAction(World.A_SHOOT);
                }
                else if(w.getDirection() == World.DIR_RIGHT)
                {
                    w.doAction(World.A_TURN_RIGHT);
                    w.doAction(World.A_TURN_RIGHT);
                    w.doAction(World.A_SHOOT);
                }
                else if(w.getDirection() == World.DIR_UP)
                {
                    w.doAction(World.A_TURN_LEFT);
                    w.doAction(World.A_SHOOT);
                }
            }
          } 
         }
        
        else if(w.hasStench(cX+1, cY-1) && w.hasArrow())
        {
            move=1;
            System.out.println("hasStench(cX+1, cY-1)");
            
             if(w.hasArrow())
           {
            if(w.isVisited(cX+1,cY))
            {
                System.out.println("if");
                if(w.getDirection() == World.DIR_DOWN)
                {
                    w.doAction(World.A_SHOOT);
                }
                else if(w.getDirection() == World.DIR_LEFT)
                {
                    w.doAction(World.A_TURN_LEFT);
                    w.doAction(World.A_SHOOT);
                }
                else if(w.getDirection() == World.DIR_RIGHT)
                {
                    w.doAction(World.A_TURN_RIGHT);
                    w.doAction(World.A_SHOOT);
                }
                else if(w.getDirection() == World.DIR_UP)
                {
                    w.doAction(World.A_TURN_LEFT);
                    w.doAction(World.A_TURN_LEFT);
                    w.doAction(World.A_SHOOT);
                }
            }
            else 
            {
                System.out.println("else");
                if(w.getDirection() == World.DIR_DOWN)
                {
                     w.doAction(World.A_TURN_LEFT);
                    w.doAction(World.A_SHOOT);
                }
                else if(w.getDirection() == World.DIR_LEFT)
                {
                     w.doAction(World.A_TURN_LEFT);
                    w.doAction(World.A_TURN_LEFT);
                    w.doAction(World.A_SHOOT);
                }
                else if(w.getDirection() == World.DIR_RIGHT)
                {
                    w.doAction(World.A_SHOOT);
                }
                else if(w.getDirection() == World.DIR_UP)
                {
                    w.doAction(World.A_TURN_RIGHT);
                    w.doAction(World.A_SHOOT);
                }
            }
           
          } 
            
        }
        else if(w.hasStench(cX-1, cY+1) && w.hasArrow())
        {
            move=1;
            System.out.println("hasStench(cX-1, cY+1)");
               if(w.hasArrow())
           {
            if(w.isVisited(cX-1, cY))
            {
                System.out.println("if");
                if(w.getDirection() == World.DIR_DOWN)
                {
                    w.doAction(World.A_TURN_LEFT);
                    w.doAction(World.A_TURN_LEFT);
                    w.doAction(World.A_SHOOT);
                }
                else if(w.getDirection() == World.DIR_LEFT)
                {
                   
                        w.doAction(World.A_TURN_RIGHT);
                    w.doAction(World.A_SHOOT);
                }
                else if(w.getDirection() == World.DIR_RIGHT)
                {
                   w.doAction(World.A_TURN_LEFT);
                    w.doAction(World.A_SHOOT);
                }
                else if(w.getDirection() == World.DIR_UP)
                {
                   
                    w.doAction(World.A_SHOOT);
                }
            }
            else 
            {
                System.out.println("else if");
                if(w.getDirection() == World.DIR_DOWN)
                {
                    w.doAction(World.A_TURN_RIGHT);
                    w.doAction(World.A_SHOOT);
                }
                else if(w.getDirection() == World.DIR_LEFT)
                {
                    
                    w.doAction(World.A_SHOOT);
                }
                else if(w.getDirection() == World.DIR_RIGHT)
                {
                   w.doAction(World.A_TURN_LEFT);
                    w.doAction(World.A_TURN_LEFT);
                    w.doAction(World.A_SHOOT);
                }
                else if(w.getDirection() == World.DIR_UP)
                {
                    w.doAction(World.A_TURN_LEFT);
                    w.doAction(World.A_SHOOT);
                }
            }
          
          } 
        }
        else if(w.hasStench(cX+1, cY-1) && w.hasArrow())
        {
            move=1;
            System.out.println("hasStench(cX+1, cY-1)");
               if(w.hasArrow())
           {
            if(w.isVisited(cX+1, cY))
            {
                System.out.println("if");
                if(w.getDirection() == World.DIR_DOWN)
                {
                   
                    w.doAction(World.A_SHOOT);
                }
                else if(w.getDirection() == World.DIR_LEFT)
                {
                   
                        w.doAction(World.A_TURN_LEFT);
                    w.doAction(World.A_SHOOT);
                }
                else if(w.getDirection() == World.DIR_RIGHT)
                {
                   w.doAction(World.A_TURN_RIGHT);
                    w.doAction(World.A_SHOOT);
                }
                else if(w.getDirection() == World.DIR_UP)
                {
                    w.doAction(World.A_TURN_LEFT);
                     w.doAction(World.A_TURN_LEFT);
                    w.doAction(World.A_SHOOT);
                }
            }
            else 
            {
                System.out.println("else");
                if(w.getDirection() == World.DIR_DOWN)
                {
                    w.doAction(World.A_TURN_LEFT);
                    w.doAction(World.A_SHOOT);
                }
                else if(w.getDirection() == World.DIR_LEFT)
                {
                    w.doAction(World.A_TURN_LEFT);
                    w.doAction(World.A_TURN_LEFT); 
                    w.doAction(World.A_SHOOT);
                }
                else if(w.getDirection() == World.DIR_RIGHT)
                {
                  
                    w.doAction(World.A_SHOOT);
                }
                else if(w.getDirection() == World.DIR_UP)
                {
                    w.doAction(World.A_TURN_RIGHT);
                    w.doAction(World.A_SHOOT);
                }
            }
          } 
            
            
        }
        else if(w.isVisited(cX-1, cY) && !w.hasBreeze(cX-1, cY) && !w.hasStench(cX-1, cY))
            {
                move=1;
                System.out.print("CASE 1");
                if(counter2==2)
                {
                    w.doAction(World.A_SHOOT);
                    w.doAction(World.A_MOVE);
                }
                else
               {
                   System.out.println(" safe move ");
                if(w.getDirection()== World.DIR_RIGHT)
                {
                w.doAction(World.A_TURN_LEFT);
                w.doAction(World.A_TURN_LEFT);
                w.doAction(World.A_MOVE);
                }
                else if(w.getDirection() == World.DIR_UP)
                {
                    w.doAction(World.A_TURN_LEFT);
                    w.doAction(World.A_MOVE);
                }
                else if(w.getDirection() == World.DIR_LEFT)
                {
                    w.doAction(World.A_MOVE);
                }
                else if(w.getDirection() ==  World.DIR_DOWN)
                {
                    w.doAction(World.A_TURN_RIGHT);
                    w.doAction(World.A_MOVE);
                }
             }
            }
            else if(w.isVisited(cX, cY+1) && !w.hasBreeze(cX, cY+1) && !w.hasStench(cX, cY))
            {
                move=1;
                System.out.println(" 2nd case");
                if(w.getDirection()== World.DIR_RIGHT)
                {
              
                w.doAction(World.A_TURN_LEFT);
                w.doAction(World.A_MOVE);
                }
                else if(w.getDirection() == World.DIR_UP)
                {
                
                    w.doAction(World.A_MOVE);
                }
                else if(w.getDirection() == World.DIR_LEFT)
                {
                    w.doAction(World.A_TURN_RIGHT);
                    w.doAction(World.A_MOVE);
                }
                else if(w.getDirection() ==  World.DIR_DOWN)
                {
                    w.doAction(World.A_TURN_RIGHT);
                    w.doAction(World.A_TURN_RIGHT);  
                    w.doAction(World.A_MOVE);
                }
            }
            else if(w.hasArrow())
            {
                move=1;
                System.out.println(" WORST CASE");
                w.doAction(World.A_SHOOT);
                w.doAction(World.A_MOVE);
            }
    }
// END OF STENCH AND BREEZE ----------------------XXXXXXXX-X----------X-XX--X-X-X--X-X-X-X--X-X-X-X-X-X-X-X--X-X-X
//  logic for STENCH---------------------------------------------------------------------------------------- 
        if(w.hasStench(cX, cY))
        {
            
// STENCH 1ST SCENARIO: ONE STENCH, ONE INVALID AND TWO VISITED 
          
                
              if(!w.isValidPosition(cX, cY-1) && move==0 && (w.isVisited(cX-1, cY) || !w.isValidPosition(cX-1, cY)) && (w.isVisited(cX, cY+1) || !w.isValidPosition(cX, cY+1))) 
              {
                  System.out.println(" STENCH BOTTOM WALL");
                  move=1;
                  if(w.getDirection() == World.DIR_RIGHT)
                  {
                      w.doAction(World.A_SHOOT);
                      w.doAction(World.A_MOVE);
                  }
                  else if(w.getDirection() == World.DIR_UP)
                  {
                      w.doAction(World.A_TURN_RIGHT);
                      w.doAction(World.A_SHOOT);
                      w.doAction(World.A_MOVE);
                  }
                  else if(w.getDirection() == World.DIR_LEFT)
                  {
                      w.doAction(World.A_TURN_RIGHT);
                      w.doAction(World.A_TURN_RIGHT);
                       w.doAction(World.A_SHOOT);
                      w.doAction(World.A_MOVE);
                  
                  }
                  else if(w.getDirection() == World.DIR_DOWN)
                  {
                      w.doAction(World.A_TURN_LEFT);
                      w.doAction(World.A_SHOOT);
                      w.doAction(World.A_MOVE);
                  
                  }
              }
                  else if(!w.isValidPosition(cX, cY-1) && move==0 && (w.isVisited(cX-1, cY) || !w.isValidPosition(cX-1, cY)) && (w.isVisited(cX+1, cY) || !w.isValidPosition(cX+1, cY) ))
               {
                   move=1;
                   System.out.println(" STENCH BOTTOM WALL2");
                      if(w.getDirection() == World.DIR_RIGHT)
                  {
                      w.doAction(World.A_TURN_LEFT);
                      w.doAction(World.A_SHOOT);
                      w.doAction(World.A_MOVE);
                  }
                  else if(w.getDirection() == World.DIR_UP)
                  {
                     
                      w.doAction(World.A_SHOOT);
                      w.doAction(World.A_MOVE);
                  }
                  else if(w.getDirection() == World.DIR_LEFT)
                  {
                     
                      w.doAction(World.A_TURN_RIGHT);
                       w.doAction(World.A_SHOOT);
                      w.doAction(World.A_MOVE);
                  
                  }
                  else if(w.getDirection() == World.DIR_DOWN)
                  {
                      w.doAction(World.A_TURN_LEFT);
                        w.doAction(World.A_TURN_LEFT);
                      w.doAction(World.A_SHOOT);
                      w.doAction(World.A_MOVE);
                  }
                  
                }
                else if( !w.isValidPosition(cX, cY-1) && move==0 && (w.isVisited(cX, cY+1) || !w.isValidPosition(cX, cY+1)) && (w.isVisited(cX+1, cY) || !w.isValidPosition(cX+1, cY)))
               {
                   move=1;
                   System.out.println(" STENCH BOTTOM WALL2");
                      if(w.getDirection() == World.DIR_RIGHT)
                  {
                      w.doAction(World.A_TURN_LEFT);
                      w.doAction(World.A_TURN_LEFT);
                      w.doAction(World.A_SHOOT);
                      w.doAction(World.A_MOVE);
                  }
                  else if(w.getDirection() == World.DIR_UP)
                  {
                      w.doAction(World.A_TURN_LEFT);
                      w.doAction(World.A_SHOOT);
                      w.doAction(World.A_MOVE);
                  }
                  else if(w.getDirection() == World.DIR_LEFT)
                  {
                       w.doAction(World.A_SHOOT);
                      w.doAction(World.A_MOVE);
                  }
                  else if(w.getDirection() == World.DIR_DOWN)
                  {
                      w.doAction(World.A_TURN_RIGHT);
                      w.doAction(World.A_SHOOT);
                      w.doAction(World.A_MOVE);
                  }
               }
       
                
              else if(!w.isValidPosition(cX+1, cY) && move==0 && (w.isVisited(cX-1, cY) || !w.isValidPosition(cX-1, cY)) && (w.isVisited(cX, cY-1) || !w.isValidPosition(cX, cY-1)))
                {
                    move=1;
                    System.out.println(" STENCH right WALL");
                     if(w.getDirection() == World.DIR_RIGHT)
                  {
                      w.doAction(World.A_TURN_LEFT);
                      w.doAction(World.A_SHOOT);
                      w.doAction(World.A_MOVE);
                  }
                  else if(w.getDirection() == World.DIR_UP)
                  {
                      w.doAction(World.A_SHOOT);
                      w.doAction(World.A_MOVE);
                  }
                  else if(w.getDirection() == World.DIR_LEFT)
                  {
                       w.doAction(World.A_TURN_RIGHT);
                       w.doAction(World.A_SHOOT);
                      w.doAction(World.A_MOVE);
                  }
                  else if(w.getDirection() == World.DIR_DOWN)
                  {
                      w.doAction(World.A_TURN_RIGHT);
                       w.doAction(World.A_TURN_RIGHT);
                      w.doAction(World.A_SHOOT);
                      w.doAction(World.A_MOVE);
                  }
                
                }
                else if(!w.isValidPosition(cX+1, cY) && move==0 && (w.isVisited(cX-1, cY) || !w.isValidPosition(cX-1, cY)) && (w.isVisited(cX, cY+1) || !w.isValidPosition(cX, cY+1)))
                {
                    move=1;
                    System.out.println(" STENCH right WALL 1");
                     if(w.getDirection() == World.DIR_RIGHT)
                  {
                      w.doAction(World.A_TURN_RIGHT);
                      w.doAction(World.A_SHOOT);
                      w.doAction(World.A_MOVE);
                  }
                  else if(w.getDirection() == World.DIR_UP)
                  {
                      w.doAction(World.A_TURN_RIGHT);
                       w.doAction(World.A_TURN_RIGHT);
                      w.doAction(World.A_SHOOT);
                      w.doAction(World.A_MOVE);
                  }
                  else if(w.getDirection() == World.DIR_LEFT)
                  {
                       w.doAction(World.A_TURN_LEFT);
                       w.doAction(World.A_SHOOT);
                      w.doAction(World.A_MOVE);
                  }
                  else if(w.getDirection() == World.DIR_DOWN)
                  {
                      
                      w.doAction(World.A_SHOOT);
                      w.doAction(World.A_MOVE);
                  }
                
                }
                else if( !w.isValidPosition(cX+1, cY) && move==0 && (w.isVisited(cX, cY-1) || !w.isValidPosition(cX, cY-1)) && (w.isVisited(cX, cY+1) || !w.isValidPosition(cX, cY+1)))
                {
                    System.out.println(" STENCH right WALL 2");
                    move=1;
                     if(w.getDirection() == World.DIR_RIGHT)
                  {
                      w.doAction(World.A_TURN_RIGHT);
                      w.doAction(World.A_TURN_RIGHT);
                      w.doAction(World.A_SHOOT);
                      w.doAction(World.A_MOVE);
                  }
                  else if(w.getDirection() == World.DIR_UP)
                  {
                      w.doAction(World.A_TURN_LEFT);
                      w.doAction(World.A_SHOOT);
                      w.doAction(World.A_MOVE);
                  }
                  else if(w.getDirection() == World.DIR_LEFT)
                  {
                     
                       w.doAction(World.A_SHOOT);
                      w.doAction(World.A_MOVE);
                  }
                  else if(w.getDirection() == World.DIR_DOWN)
                  {
                      w.doAction(World.A_TURN_RIGHT);
                      w.doAction(World.A_SHOOT);
                      w.doAction(World.A_MOVE);
                  }
                
                } 
                if(!w.isValidPosition(cX, cY+1) && move==0 && (w.isVisited(cX+1, cY) || !w.isValidPosition(cX+1, cY)) && (w.isVisited(cX-1, cY) || !w.isValidPosition(cX-1, cY)))
                        {
                            move=1;
                            
                System.out.println(" STENCH TOP WALL");
                            if(w.getDirection() == World.DIR_RIGHT)
                  {
                     
                      w.doAction(World.A_TURN_RIGHT);
                      w.doAction(World.A_SHOOT);
                      w.doAction(World.A_MOVE);
                  }
                  else if(w.getDirection() == World.DIR_UP)
                  {
                      w.doAction(World.A_TURN_LEFT);
                       w.doAction(World.A_TURN_LEFT);
                      w.doAction(World.A_SHOOT);
                      w.doAction(World.A_MOVE);
                  }
                  else if(w.getDirection() == World.DIR_LEFT)
                  {
                      w.doAction(World.A_TURN_LEFT);
                       w.doAction(World.A_SHOOT);
                      w.doAction(World.A_MOVE);
                  }
                  else if(w.getDirection() == World.DIR_DOWN)
                  {
                     
                      w.doAction(World.A_SHOOT);
                      w.doAction(World.A_MOVE);
                  }
                        
                        }
                else if(!w.isValidPosition(cX, cY+1) && move==0 && (w.isVisited(cX+1, cY) || !w.isValidPosition(cX+1, cY)) && (w.isVisited(cX, cY-1) || !w.isValidPosition(cX, cY-1)))
                {
                    move=1;
                    
                System.out.println(" STENCH TOP WALL    1");
                    if(w.getDirection() == World.DIR_RIGHT)
                  {
                     w.doAction(World.A_TURN_RIGHT);
                      w.doAction(World.A_TURN_RIGHT);
                      w.doAction(World.A_SHOOT);
                      w.doAction(World.A_MOVE);
                  }
                  else if(w.getDirection() == World.DIR_UP)
                  {
                      w.doAction(World.A_TURN_LEFT);
                      w.doAction(World.A_SHOOT);
                      w.doAction(World.A_MOVE);
                  }
                  else if(w.getDirection() == World.DIR_LEFT)
                  {
                      
                       w.doAction(World.A_SHOOT);
                      w.doAction(World.A_MOVE);
                  }
                  else if(w.getDirection() == World.DIR_DOWN)
                  {
                     w.doAction(World.A_TURN_RIGHT);
                      w.doAction(World.A_SHOOT);
                      w.doAction(World.A_MOVE);
                  }
                
                }
                else if(!w.isValidPosition(cX, cY+1) && move==0 && (w.isVisited(cX-1, cY) || !w.isValidPosition(cX-1, cY)) && (w.isVisited(cX, cY-1) || !w.isValidPosition(cX, cY-1)))
                {
                    
                System.out.println(" STENCH TOP WALL 2");
                    move=1;
                     if(w.getDirection() == World.DIR_RIGHT)
                  {
                     
                      w.doAction(World.A_SHOOT);
                      w.doAction(World.A_MOVE);
                  }
                  else if(w.getDirection() == World.DIR_UP)
                  {
                     
                     w.doAction(World.A_TURN_RIGHT);
                      w.doAction(World.A_SHOOT);
                      w.doAction(World.A_MOVE);
                  }
                  else if(w.getDirection() == World.DIR_LEFT)
                  {
                      w.doAction(World.A_TURN_RIGHT);
                      w.doAction(World.A_TURN_RIGHT);
                       w.doAction(World.A_SHOOT);
                      w.doAction(World.A_MOVE);
                  }
                  else if(w.getDirection() == World.DIR_DOWN)
                  {
                       w.doAction(World.A_TURN_LEFT);
                      w.doAction(World.A_SHOOT);
                      w.doAction(World.A_MOVE);
                  }
                
                }
               
                 if(!w.isValidPosition(cX-1, cY) && move==0 && (w.isVisited(cX+1, cY) || !w.isValidPosition(cX+1, cY)) && (w.isVisited(cX, cY+1) || !w.isValidPosition(cX, cY+1)))
                 {
                     move=1;
                      System.out.println(" STENCH LEFT WALL");
                     if(w.getDirection() == World.DIR_RIGHT)
                  {
                      w.doAction(World.A_TURN_RIGHT);
                      w.doAction(World.A_SHOOT);
                      w.doAction(World.A_MOVE);
                  }
                  else if(w.getDirection() == World.DIR_UP)
                  {
                      w.doAction(World.A_TURN_RIGHT);
                     w.doAction(World.A_TURN_RIGHT);
                      w.doAction(World.A_SHOOT);
                      w.doAction(World.A_MOVE);
                  }
                  else if(w.getDirection() == World.DIR_LEFT)
                  {
                       w.doAction(World.A_TURN_LEFT);
                       w.doAction(World.A_SHOOT);
                      w.doAction(World.A_MOVE);
                  }
                  else if(w.getDirection() == World.DIR_DOWN)
                  {
                      
                      w.doAction(World.A_SHOOT);
                      w.doAction(World.A_MOVE);
                  }
                 
                 }
                 else if(!w.isValidPosition(cX-1, cY) && move==0 && (w.isVisited(cX+1, cY) || !w.isValidPosition(cX+1, cY)) && (w.isVisited(cX, cY-1) || !w.isValidPosition(cX, cY-1)))
                 {
                     move=1;
                      System.out.println(" STENCH LEFT WALL 2");
                        if(w.getDirection() == World.DIR_RIGHT)
                  {
                      w.doAction(World.A_TURN_LEFT);
                      w.doAction(World.A_SHOOT);
                      w.doAction(World.A_MOVE);
                  }
                  else if(w.getDirection() == World.DIR_UP)
                  {
                     
                      w.doAction(World.A_SHOOT);
                      w.doAction(World.A_MOVE);
                  }
                  else if(w.getDirection() == World.DIR_LEFT)
                  {
                       w.doAction(World.A_TURN_RIGHT);
                       w.doAction(World.A_SHOOT);
                      w.doAction(World.A_MOVE);
                  }
                  else if(w.getDirection() == World.DIR_DOWN)
                  {
                       w.doAction(World.A_TURN_RIGHT);
                     w.doAction(World.A_TURN_RIGHT);
                      w.doAction(World.A_SHOOT);
                      w.doAction(World.A_MOVE);
                  }
                     
                 }
                 else if( !w.isValidPosition(cX-1, cY) && move==0 && (w.isVisited(cX, cY-1) || !w.isValidPosition(cX, cY-1)) && (w.isVisited(cX, cY+1) || !w.isValidPosition(cX, cY+1)))
                 {
                     move=1;
                      System.out.println(" STENCH LEFT WALL 2");
                      if(w.getDirection() == World.DIR_RIGHT)
                  {
                     
                      w.doAction(World.A_SHOOT);
                      w.doAction(World.A_MOVE);
                  }
                  else if(w.getDirection() == World.DIR_UP)
                  {
                      w.doAction(World.A_TURN_RIGHT);
                      w.doAction(World.A_SHOOT);
                      w.doAction(World.A_MOVE);
                  }
                  else if(w.getDirection() == World.DIR_LEFT)
                  {
                       w.doAction(World.A_TURN_RIGHT);
                        w.doAction(World.A_TURN_RIGHT);
                       w.doAction(World.A_SHOOT);
                      w.doAction(World.A_MOVE);
                  }
                  else if(w.getDirection() == World.DIR_DOWN)
                  {
                       w.doAction(World.A_TURN_LEFT);
                      w.doAction(World.A_SHOOT);
                      w.doAction(World.A_MOVE);
                  }
                 }
            
// STENCH 2ND SCENARIO: TWO STENCHES      
            if(w.hasStench(cX-1, cY+1) && move==0)
            {
                move=1;
                System.out.println("hasStench(cX-1, cY+1) and move is "+move);
                 if(w.getDirection() == World.DIR_DOWN)
                 {   
                     System.out.print("  DOWN  ");
                     if(w.hasArrow())
                       {  
                          System.out.print("hasarrow  "); 
                           if(w.isVisited(cX, cY+1)) 
                           {    System.out.print("9: isVisited(cX, cY+1)");
                                w.doAction(World.A_TURN_RIGHT);
                                w.doAction(World.A_SHOOT);
                                w.doAction(World.A_MOVE);
                           }
                           if(w.isVisited(cX-1, cY))  //extra
                           {
                                System.out.print("10: isVisited(cX-1, cY)");
                                w.doAction(World.A_TURN_RIGHT);
                                w.doAction(World.A_TURN_RIGHT);
                                w.doAction(World.A_SHOOT);
                                w.doAction(World.A_MOVE);
                           }
                           else
                           {
                               System.out.print("11: not visited x,y+1 and x-1,y");
                               w.doAction(World.A_TURN_RIGHT);
                               w.doAction(World.A_TURN_RIGHT);
                               w.doAction(World.A_MOVE);
                           }
                       }                                
                    else
                       {      
                            System.out.print("11_1: no arrow");
                            w.doAction(World.A_TURN_RIGHT);
                            w.doAction(World.A_TURN_RIGHT);
                            w.doAction(World.A_MOVE);
                       } 
               
                 }
                 
                 else if(w.getDirection() == World.DIR_RIGHT)
                  {
                      System.out.println("  RIGHT   ");
                      if(w.hasArrow())
                      {
                          System.out.print("hasarrow");
                          if(w.isVisited(cX-1, cY))
                          {
                          System.out.println("12: isVisited(cX-1, cY)");
                          w.doAction(World.A_TURN_LEFT);
                          w.doAction(World.A_SHOOT);
                          w.doAction(World.A_MOVE);
                          }
                          else if(w.isVisited(cX, cY+1))
                          {
                          System.out.print("13: isVisited(cX, cY+1)");
                          w.doAction(World.A_TURN_LEFT);
                          w.doAction(World.A_TURN_LEFT);
                          w.doAction(World.A_SHOOT);
                          w.doAction(World.A_MOVE);
                          }
                          else
                          {
                            System.out.println("13.1: not voisited x-1,y & x,y+1");
                            w.doAction(World.A_MOVE);
                          }        
                      }
                      else
                      {
                          System.out.print(" no arrow ");
                          if(w.isVisited(cX-1, cY))
                          {
                          System.out.println("14: isVisited(cX-1, cY)");
                          w.doAction(World.A_TURN_LEFT);
                          w.doAction(World.A_TURN_LEFT);
                          w.doAction(World.A_MOVE);
                          }
                          else
                          {
                              System.out.println("14.1: notVisited(cX-1, cY");
                              w.doAction(World.A_MOVE);
                          }
                      }
                  } 
                 else if (w.getDirection() == World.DIR_LEFT)
                 { 
                     System.out.print(" LEFT    ");
                    if(w.hasArrow())
                    {
                        System.out.print(" hasArrow");
                        if(w.isVisited(cX-1, cY))
                        {
                          System.out.println("15: isVisited(cX-1, cY)");
                          w.doAction(World.A_TURN_RIGHT);
                          w.doAction(World.A_SHOOT);
                          w.doAction(World.A_MOVE);
                        }
                        else if(w.isVisited(cX, cY+1))
                        {
                          System.out.println("16 : isVisited(cX, cY+1)");
                          w.doAction(World.A_SHOOT);
                          w.doAction(World.A_MOVE);
                        }
                        else
                        {
                            System.out.println("16.1: not visited x-1,y & x,y+1");
                         w.doAction(World.A_TURN_LEFT);
                        w.doAction(World.A_TURN_LEFT);
                        w.doAction(World.A_MOVE);
                        }
                        /* if(w.isUnknown(cX-1, cY) && w.isUnknown(cX, cY+1))
                        {
                          w.doAction(World.A_SHOOT);
                          w.doAction(World.A_MOVE);
                          System.out.println("17.1");
                        }*/
                   }
                   else
                   {
                       System.out.println("17: no arrow"); 
                        w.doAction(World.A_TURN_LEFT);
                        w.doAction(World.A_TURN_LEFT);
                        w.doAction(World.A_MOVE);
                   }
                
                 } 
                 if (w.getDirection() == World.DIR_UP)
                 {
                     System.out.println("   UP  ");
                     if(w.hasArrow())
                     {
                         System.out.print("hasArrow");
                         if(w.isVisited(cX-1, cY))
                          {
                             System.out.println("18: isVisited(cX-1, cY)");
                             w.doAction(World.A_SHOOT);
                            w.doAction(World.A_MOVE);
                         }
                         if(w.isVisited(cX, cY+1))
                         {
                            System.out.println("19: isVisited(cX, cY+1)");
                             w.doAction(World.A_TURN_LEFT);
                            w.doAction(World.A_SHOOT);
                            w.doAction(World.A_MOVE);
                         }
                         else
                         {
                             System.out.println("20: notVisited x-1,y & x,y+1");
                             w.doAction(World.A_MOVE);
                         }
                        /* if(w.isUnknown(cX-1, cY) && w.isUnknown(cX, cY+1))
                         {
                             w.doAction(World.A_SHOOT);
                             w.doAction(World.A_MOVE);
                             System.out.println("20");
                         }*/
                     }
                     else
                     {
                         System.out.print("noArrow");
                         if(w.isVisited(cX, cY-1))
                         {
                         System.out.println("21: isVisited(cX, cY-1)");
                         w.doAction(World.A_TURN_LEFT);
                         w.doAction(World.A_TURN_LEFT);
                         w.doAction(World.A_MOVE); 
                         }
                         if(w.isVisited(cX-1, cY))
                         {
                         System.out.println("22: isVisited(cX-1, cY)");
                         w.doAction(World.A_TURN_LEFT);
                         w.doAction(World.A_MOVE); 
                         }
                         else
                         {
                         System.out.println("23: not Visited x,y-1 & x-1,y");
                         w.doAction(World.A_TURN_RIGHT); //if ri8 tile is valid
                          w.doAction(World.A_MOVE); 
                         }
                     }
                 }
             }  // stench cx-1,cY+1
            
            else if (w.hasStench(cX+1, cY-1) && move==0)
             {
                 move=1;
                 System.out.println(" hasStench(cX+1, cY-1)");
                if(w.getDirection() == World.DIR_UP)
                {
                    System.out.print("  UP  ");
                  if(w.hasArrow())
                  {
                   System.out.print("hasArrow");   
                      if(w.isVisited(cX, cY-1))
                      {
                          System.out.println("24: isVisited(cX, cY-1) ");
                        w.doAction(World.A_TURN_RIGHT);
                        w.doAction(World.A_SHOOT);
                        w.doAction(World.A_MOVE);
                      }
                      else if(w.isVisited(cX+1, cY))
                      {
                          System.out.println("25: isVisited(cX+1, cY)");
                          w.doAction(World.A_TURN_RIGHT);
                          w.doAction(World.A_TURN_RIGHT);
                          w.doAction(World.A_SHOOT);            
                          w.doAction(World.A_MOVE);
                      }
                      else
                      {
                          System.out.println("26: not visited x,y-1 & x+1,y");
                      w.doAction(World.A_TURN_RIGHT);
                      w.doAction(World.A_TURN_RIGHT); // check other cases if necessary
                      w.doAction(World.A_MOVE);
                      }
                  }
                  else
                  {
                      System.out.println("27: no arrow");
                      w.doAction(World.A_TURN_RIGHT);
                      w.doAction(World.A_TURN_RIGHT);
                      w.doAction(World.A_MOVE);
                  }
                }
               else if(w.getDirection() == World.DIR_DOWN)
               {
                   System.out.print("   UP  ");
                   if(w.hasArrow())
                   {
                        System.out.print(" hasArrow");
                       if(w.isVisited(cX, cY-1))
                       {
                           
                           System.out.println("28: isVisited(cX, cY-1)");
                       w.doAction(World.A_TURN_LEFT);
                       w.doAction(World.A_SHOOT);
                       w.doAction(World.A_MOVE);
                       }
                       else if(w.isVisited(cX+1, cY))
                       {
                           System.out.println("29: isVisited(cX+1, cY) ");
                       w.doAction(World.A_SHOOT);
                       w.doAction(World.A_MOVE);
                       }
                       else
                       {
                           System.out.println("30: not visited x,y-1 & x+1,y");
                        w.doAction(World.A_TURN_RIGHT);
                        w.doAction(World.A_TURN_RIGHT);
                            if(!w.isValidPosition(cX, cY+1))
                            {
                            System.out.println("31: top left corner issue");
                            w.doAction(World.A_TURN_RIGHT); //top left corner issue ( ri8 ri8 turns up which is left corner)
                            }
                            w.doAction(World.A_MOVE);
                       } 
                   }
                   else
                   {
                       System.out.println("32: noArrow");
                        w.doAction(World.A_TURN_RIGHT);
                        w.doAction(World.A_TURN_RIGHT);
                         if(!w.isValidPosition(cX, cY+1))
                            {
                             System.out.println("33: top left corner issue");
                            w.doAction(World.A_TURN_RIGHT); //top left corner issue ( ri8 ri8 turns up which is left corner)
                            }
                            w.doAction(World.A_MOVE);  
                   }
               }
               else if(w.getDirection() == World.DIR_RIGHT)
                {
                    System.out.print("  RIGHT   ");
                    if(w.hasArrow())
                    {
                        System.out.print("hasArrow");
                        if(w.isVisited(cX+1, cY))
                        {
                            System.out.println("34: isVisited(cX+1, cY ");
                        w.doAction(World.A_TURN_RIGHT);
                        w.doAction(World.A_SHOOT);
                        w.doAction(World.A_MOVE);
                        }
                        if(w.isVisited(cX, cY-1))
                        {
                            System.out.println("35: isVisited(cX, cY-1)");
                        w.doAction(World.A_SHOOT);
                        w.doAction(World.A_MOVE);
                        }
                        else
                        {
                            System.out.println("36: not Visited x+1,y & x,y-1");
                        w.doAction(World.A_TURN_RIGHT);
                        w.doAction(World.A_TURN_RIGHT);
                        if(!w.isValidPosition(cX-1, cY))
                           { System.out.println("37: notvalid x-1,y");
                            w.doAction(World.A_TURN_RIGHT);
                           }
                        w.doAction(World.A_MOVE);
                        }
                    }
                    else
                    {
                        System.out.println("38: no Arrow");
                        w.doAction(World.A_TURN_RIGHT);
                        w.doAction(World.A_TURN_RIGHT);
                        if(!w.isValidPosition(cX-1, cY))
                        { System.out.println("39: notvalid x-1,y");
                            w.doAction(World.A_TURN_RIGHT);
                        }
                        w.doAction(World.A_MOVE);
                    }
                }
               else if(w.getDirection() == World.DIR_LEFT)
                {
                    System.out.print("  LEFT    ");
                    if(w.hasArrow())
                    {
                        System.out.print(" hasArrow");
                     if(w.isVisited(cX+1, cY))
                        {
                            System.out.println("40: isVisited(cX+1, cY) ");
                        w.doAction(World.A_TURN_LEFT);
                        w.doAction(World.A_SHOOT);
                        w.doAction(World.A_MOVE);
                        }
                     else if(w.isVisited(cX, cY-1))
                        {
                            System.out.println("41: isVisited(cX, cY-1)");
                        w.doAction(World.A_TURN_LEFT);
                        w.doAction(World.A_TURN_LEFT);
                        w.doAction(World.A_SHOOT);
                        w.doAction(World.A_MOVE);
                        }
                        else
                        {
                           System.out.println("42: not visited x+1,y & x,y-1");
                            w.doAction(World.A_MOVE);
                        }
                    }
                    else
                    {
                        System.out.println("43: noArrow");    
                        w.doAction(World.A_MOVE);
                    }
                }
             } // END OF STENCH(X+1,Y-1)
            
            else if (w.hasStench(cX-1, cY-1) && move==0)
            {
                move=1;
            System.out.println("43_1: hasStench(cX-1, cY-1) ");   
            if(w.getDirection() == World.DIR_DOWN)
            {
                System.out.println("43_2: DOWN");
                if(w.hasArrow())
                {
                    System.out.println("hasArrow");
                    if(w.isVisited(cX-1, cY))
                    {
                    System.out.println("43_3: isVisited(cX-1, cY)"); 
                    w.doAction(World.A_SHOOT);
                    w.doAction(World.A_MOVE);
                     }
                    else if(w.isVisited(cX, cY-1))
                    {
                     System.out.println("43_4: isVisited(cX, cY-1)");
                    w.doAction(World.A_TURN_RIGHT);
                    w.doAction(World.A_SHOOT);
                    w.doAction(World.A_MOVE);
                    }
                     else
                    {
                    System.out.println("43_5: notVisited x-1,y & x,y-1"); 
                    w.doAction(World.A_TURN_LEFT);
                    w.doAction(World.A_TURN_LEFT);
                    w.doAction(World.A_MOVE);
                    }
                }
                else
                {
                   
                 System.out.println("43_6: noArrow"); 
                    w.doAction(World.A_TURN_LEFT);
                    w.doAction(World.A_TURN_LEFT);
                    w.doAction(World.A_MOVE);
                }
                
            }
           else if(w.getDirection() == World.DIR_LEFT)
            {
                System.out.print("  LEFT    ");
                if(w.hasArrow())
                {
                    System.out.print(" hasArrow");
                    if(w.isVisited(cX-1, cY))
                    {  
                      System.out.println("43_7: isVisited(cX-1, cY)"); 
                      w.doAction(World.A_TURN_LEFT);
                      w.doAction(World.A_SHOOT);
                      w.doAction(World.A_MOVE);  
                    }
                    else if(w.isVisited(cX, cY-1))
                    {
                        System.out.println("43_8: isVisited(cX, cY-1)"); 
                        w.doAction(World.A_SHOOT);
                        w.doAction(World.A_MOVE);
                    }
                    else
                    {
                        System.out.println("43_9: not Visited x-1,y & x,y-1"); 
                        w.doAction(World.A_TURN_LEFT);
                        w.doAction(World.A_TURN_LEFT);
                        w.doAction(World.A_MOVE);
                    }  
                }
                else
                {
                     System.out.println("43_10: noArrow"); 
                        w.doAction(World.A_TURN_LEFT);
                        w.doAction(World.A_TURN_LEFT);
                        w.doAction(World.A_MOVE);       
                }
            }
           else if(w.getDirection() == World.DIR_RIGHT)
            {
                System.out.println("    RIGHT   ");
                if(w.hasArrow())
                {
                    
                    System.out.println("43_11: hasArrow");
                    if(w.isVisited(cX-1, cY))
                    { 
                        System.out.println("43_12: isVisited(cX-1, cY");
                        w.doAction(World.A_TURN_RIGHT);
                        w.doAction(World.A_SHOOT);
                        w.doAction(World.A_MOVE);
                    }
                    else if(w.isVisited(cX, cY-1))
                    {
                        System.out.println("43_13:isVisited(cX, cY-1 ");
                        w.doAction(World.A_TURN_LEFT);
                        w.doAction(World.A_TURN_LEFT);
                        w.doAction(World.A_SHOOT);
                        w.doAction(World.A_MOVE);
                    }
                    else
                    {
                        System.out.println("43_14: not visited x-1,y & x,y-1");
                        w.doAction(World.A_MOVE);
                    }
                }
                else
                {
                    System.out.print("noArrow");
                        if(w.isVisited(cX-1, cY))
                        {
                            System.out.println("43_15:isVisited(cX-1, cY ");
                            w.doAction(World.A_TURN_LEFT);
                            w.doAction(World.A_TURN_LEFT);
                            w.doAction(World.A_MOVE);
                        }
                        else
                        {
                            System.out.println("43_16: not Visited(cX-1, cY");
                            w.doAction(World.A_MOVE);
                        }
                }
            }
           else if(w.getDirection() == World.DIR_UP)
            {
                System.out.println("43_17: UP   ");
                if(w.hasArrow())
                    {
                        System.out.print("hasArrow");
                        if(w.isVisited(cX-1, cY))
                        {
                            System.out.println("43_18: isVisited(cX-1, cY)");
                            w.doAction(World.A_TURN_LEFT);
                            w.doAction(World.A_TURN_LEFT);
                            w.doAction(World.A_SHOOT);
                            w.doAction(World.A_MOVE);    
                        }
                        else if(w.isVisited(cX, cY-1))
                        {
                            System.out.println("43_19: isVisited(cX, cY-1)");
                            w.doAction(World.A_TURN_LEFT);
                            w.doAction(World.A_SHOOT);
                            w.doAction(World.A_MOVE);  
                        }
                        else
                        {
                            System.out.println("43_20: not visited x-1,y & x,y-1");
                            w.doAction(World.A_MOVE);
                        }
                    }
                else
                    {
                        System.out.print("noArrow");
                        if(w.isVisited(cX, cY-1))
                        {
                            System.out.println("43_21: isVisited(cX, cY-1)");
                            w.doAction(World.A_TURN_LEFT);
                            w.doAction(World.A_TURN_LEFT);
                            w.doAction(World.A_MOVE);    
                        }
                        else
                        {
                            System.out.println("43_22: Not Visited(cX, cY-1)");
                            w.doAction(World.A_MOVE);          
                        }
                    }
                }                
            }// END OF STENCH X-1,Y-1
            else if(w.hasStench(cX+1, cY+1) && move==0)
            {
                
                move=1;
            System.out.println(" hasStench(cX+1, cY+1)" );
                if(w.getDirection() == World.DIR_DOWN)
                {
                    System.out.println(" DOWN ");
                    if(w.hasArrow())
                    {
                        System.out.print("hasArrow");
                        if(w.isVisited(cX, cY+1))
                        {
                            System.out.println("43_25: isVisited(cX, cY+1) ");
                            w.doAction(World.A_TURN_LEFT);
                            w.doAction(World.A_SHOOT);
                            w.doAction(World.A_MOVE);
                        
                        }
                        else if (w.isVisited(cX+1, cY))
                        {
                            System.out.println("43_26: isVisited(cX+1, cY)");
                            w.doAction(World.A_TURN_LEFT);
                            w.doAction(World.A_TURN_LEFT);
                            w.doAction(World.A_SHOOT);
                            w.doAction(World.A_MOVE);
                        }
                        else
                        {
                            System.out.println("43_27: not Visited x,y+1 & x+1,y");
                            w.doAction(World.A_MOVE);
                        }
                    }
                    else
                    {
                        System.out.print(" noArrow");
                        if(w.isVisited(cX, cY+1))
                        {
                            System.out.println("43_28: isVisited(cX, cY+1)");
                            w.doAction(World.A_TURN_LEFT);
                            w.doAction(World.A_TURN_LEFT);
                            w.doAction(World.A_MOVE);
                        }
                        else
                        {
                            System.out.println("43_29: not Visited(cX, cY+1)");
                            w.doAction(World.A_MOVE);
                        }
                    
                    }
                }
                else if(w.getDirection() == World.DIR_LEFT)
                {
                   System.out.print(" LEFT ");
                    if(w.hasArrow())
                    {
                        System.out.print(" hasArrow");
                        if(w.isVisited(cX+1, cY))
                        {
                             System.out.println("43_30: isVisited(cX+1, cY)");
                            w.doAction(World.A_TURN_RIGHT);
                            w.doAction(World.A_SHOOT);
                            w.doAction(World.A_MOVE);
                        }
                        else if(w.isVisited(cX, cY+1))
                        {
                             System.out.println("43_31: isVisited(cX, cY+1)");
                            w.doAction(World.A_TURN_RIGHT);
                            w.doAction(World.A_TURN_RIGHT);
                            w.doAction(World.A_SHOOT);
                            w.doAction(World.A_MOVE);
                        }
                        else
                        {
                             System.out.println("43_32: not Visited x+1,y & x,y+1");
                             w.doAction(World.A_MOVE);
                        }
                    }
                    else
                    {
                        System.out.print(" noArrow ");
                        if(w.isVisited(cX+1, cY))
                        {
                            System.out.println("43_33: isVisited(cX+1, cY)");
                            w.doAction(World.A_TURN_RIGHT);
                            w.doAction(World.A_TURN_RIGHT);
                            w.doAction(World.A_MOVE);
                        }
                        else
                        {
                             System.out.println("43_34: not Visited(cX+1, cY)");
                             w.doAction(World.A_MOVE);    
                        }
                    }
                }
                else if(w.getDirection() == World.DIR_RIGHT)
                {
                    System.out.println("    Right ");
                    if(w.hasArrow())
                    {
                        System.out.print(" hasArrow");
                        if(w.isVisited(cX, cY+1))
                        {
                            System.out.println("43_35: isVisited(cX, cY+1)");
                            w.doAction(World.A_SHOOT);
                             w.doAction(World.A_MOVE); 
                        }
                        else if(w.isVisited(cX+1, cY))
                        {
                            System.out.println("43_36: isVisited(cX+1, cY)");
                            w.doAction(World.A_TURN_LEFT);
                            w.doAction(World.A_SHOOT);
                             w.doAction(World.A_MOVE);          
                        }
                        else
                        {
                            System.out.println("43_37: not visited x,y+1 & x+1,y");
                             w.doAction(World.A_MOVE); 
                        }
                    }
                    else
                    {
                        System.out.print("noArrow");
                        if(w.isVisited(cX+1, cY))
                        {
                        System.out.println("43_38: isVisited(cX+1, cY)");
                             w.doAction(World.A_MOVE);
                        }
                        else if(w.isUnknown(cX, cY+1) && w.isVisited(cX-1, cY+1) && !w.hasStench(cX-1, cY+1))
                        {
                            System.out.println("43_38_1: isUnknown(cX, cY+1) & isVisited(cX-1, cY+1) & !w.hasStench(cX-1, cY+1)");
                            w.doAction(World.A_TURN_LEFT);
                             w.doAction(World.A_MOVE);
                            
                        }
                        else
                        {
                            System.out.println("43_39: not Visited(cX+1, cY)");
                            w.doAction(World.A_TURN_LEFT);
                            w.doAction(World.A_TURN_LEFT);
                             w.doAction(World.A_MOVE);
                        }
                    }
                }
               else if(w.getDirection() == World.DIR_UP)
                {
                    System.out.print("  UP  ");
                    if(w.hasArrow())
                    {
                        System.out.print(" hasArrow");
                        if(w.isVisited(cX+1, cY))
                        {
                            System.out.println("43_40: isVisited(cX+1, cY)");
                            w.doAction(World.A_SHOOT);
                            w.doAction(World.A_MOVE);
                        }
                        else if(w.isVisited(cX, cY+1))
                        {
                            System.out.println("43_41: isVisited(cX, cY+1)");
                            w.doAction(World.A_TURN_RIGHT);
                            w.doAction(World.A_SHOOT);
                            w.doAction(World.A_MOVE);
                        }
                         else
                        {
                            System.out.println("43_42: not visited x+1,y & x,y+1");
                            w.doAction(World.A_TURN_RIGHT);
                            w.doAction(World.A_TURN_RIGHT);
                            w.doAction(World.A_MOVE);
                        }
                    }
                    else
                    {
                        System.out.print("noArrow");
                        if(w.isVisited(cX+1, cY))
                        {
                            System.out.println("43_43: isVisited(cX+1, cY)");
                            w.doAction(World.A_TURN_RIGHT);
                            w.doAction(World.A_MOVE);
                        }
                        else
                        {
                            System.out.println("43_44: Not Visited(cX+1, cY)");
                            w.doAction(World.A_TURN_RIGHT);
                            w.doAction(World.A_TURN_RIGHT);
                            w.doAction(World.A_MOVE);
                        }
                    }
                
                }
               else{
                   System.out.println("43_44: NO CONDITIONS PLEASE CONSIDER");
               }
            }// END OF STENCH X+1,Y+1
            
 //1ST TILE       
            else if(w.isUnknown(cX+1, cY) && !w.isValidPosition(cX-1, cY) && w.isUnknown(cX, cY+1) && !w.isValidPosition(cX, cY-1) && move==0)  // 1st tile with stench
            {
                move=1;
            System.out.println("44: 1st tile with stench");
            w.doAction(World.A_SHOOT);
            w.doAction(World.A_MOVE);
            }
//STENCH 3RD SCENARIO: SAFE MOVES            
            else if(!w.hasStench(cX+1, cY-1) && w.isValidPosition(cX+1, cY-1) && w.isVisited(cX+1, cY-1) && move==0) 
            {
                System.out.println("!w.hasStench(cX+1, cY-1)");
                move=1;
                if(w.isVisited(cX+1, cY))
                {
                    if(w.getDirection() == World.DIR_LEFT)
                    {
                    System.out.println("45_001");
                    w.doAction(World.A_TURN_LEFT);
                    w.doAction(World.A_MOVE);
                    }
                    else if(w.getDirection() == World.DIR_DOWN)
                    {
                    System.out.println("45_002");
                    w.doAction(World.A_MOVE);
                    }   
                    else if(w.getDirection() == World.DIR_RIGHT)
                    {
                       System.out.println("45_003");
                       w.doAction(World.A_TURN_RIGHT);
                       w.doAction(World.A_MOVE);
                    }
                    else if(w.getDirection() == World.DIR_UP)
                    {
                       System.out.println("45_004");
                       w.doAction(World.A_TURN_RIGHT);
                       w.doAction(World.A_TURN_RIGHT);
                       w.doAction(World.A_MOVE); 
                    }
                }
                else if(w.isVisited(cX, cY-1))
                {
                    if(w.getDirection() == World.DIR_LEFT)
                    {
                    System.out.println("45_005");
                     w.doAction(World.A_TURN_RIGHT);
                       w.doAction(World.A_TURN_RIGHT);
                    w.doAction(World.A_MOVE);
                    }
                    else if(w.getDirection() == World.DIR_DOWN)
                    {
                    System.out.println("45_006");
                    w.doAction(World.A_TURN_LEFT);
                    w.doAction(World.A_MOVE);
                    }   
                    else if(w.getDirection() == World.DIR_RIGHT)
                    {
                       System.out.println("45_007");
                       w.doAction(World.A_MOVE);
                    }
                    else if(w.getDirection() == World.DIR_UP)
                    {
                       System.out.println("45_008");
                       w.doAction(World.A_TURN_RIGHT);
                       w.doAction(World.A_MOVE); 
                    }
                }
                else
                {
                    
                    if(w.getDirection() == World.DIR_UP|| w.getDirection() == World.DIR_LEFT)
                    {
                        System.out.println("45_009 MAY BE OTHER CASE");
                        w.doAction(World.A_TURN_RIGHT);
                       w.doAction(World.A_TURN_RIGHT);
                    w.doAction(World.A_MOVE);
                    
                    }
                    else
                    {
                        System.out.println("45_010 MAY BE OTHER CASE");
                        w.doAction(World.A_MOVE);
                    }
                }
                    
            }
// CASE 2 STENCH AND ONE empty tile VISited           
            else if (!w.hasStench(cX+1, cY+1) && w.isValidPosition(cX+1, cY+1) && w.isVisited(cX+1, cY+1) && move==0)
            {
                 System.out.println("!w.hasStench(cX+1, cY+1)");
                move=1;
                if(w.isVisited(cX+1, cY))
                {
                    if(w.getDirection() == World.DIR_UP)
                    {
                        System.out.println("45_011");
                        w.doAction(World.A_MOVE);
                    
                    }
                    else if(w.getDirection() == World.DIR_DOWN)
                    {
                          System.out.println("45_012");
                           w.doAction(World.A_TURN_RIGHT);
                       w.doAction(World.A_TURN_RIGHT);
                        w.doAction(World.A_MOVE);
                    }
                    else if(w.getDirection() == World.DIR_LEFT)
                    {
                          System.out.println("45_013");
                           w.doAction(World.A_TURN_RIGHT);
                        w.doAction(World.A_MOVE);
                    }
                    else if(w.getDirection() == World.DIR_RIGHT)
                    {
                          System.out.println("45_014");
                           w.doAction(World.A_TURN_LEFT);
                        w.doAction(World.A_MOVE);
                    }
                }
                else if(w.isVisited(cX, cY+1))
                {
                    if(w.getDirection() == World.DIR_UP)
                    {
                        System.out.println("45_015");
                         w.doAction(World.A_TURN_RIGHT);
                        w.doAction(World.A_MOVE);
                    }
                    else if(w.getDirection() == World.DIR_DOWN)
                    {
                          System.out.println("45_016");
                           w.doAction(World.A_TURN_LEFT);
                        w.doAction(World.A_MOVE);
                    }
                    else if(w.getDirection() == World.DIR_LEFT)
                    {
                          System.out.println("45_017");
                           w.doAction(World.A_TURN_RIGHT);
                       w.doAction(World.A_TURN_RIGHT);
                        w.doAction(World.A_MOVE);
                    }
                    else if(w.getDirection() == World.DIR_RIGHT)
                    {
                          System.out.println("45_018");
                        w.doAction(World.A_MOVE);
                    
                    }
                }
                else
                {
                    if(w.getDirection() == World.DIR_LEFT || w.getDirection() == World.DIR_DOWN)
                    {
                        System.out.println("45_019");
                           w.doAction(World.A_TURN_RIGHT);
                       w.doAction(World.A_TURN_RIGHT);
                        w.doAction(World.A_MOVE);
                    }
                    else
                    {
                        System.out.println("45_020");
                        w.doAction(World.A_MOVE);    
                    }
                }
            }
// CASE 3 STENCH AND ONE empty tile VISited            
            else if(!w.hasStench(cX-1, cY+1) && w.isVisited(cX-1, cY+1) && w.isVisited(cX-1, cY+1) && move==0)
            {
                 System.out.println("!w.hasStench(cX-1, cY+1)");
                move=1;
                if(w.isVisited(cX, cY+1))
                {
                     if(w.getDirection() == World.DIR_UP)
                    {
                        System.out.println("45_021");
                         w.doAction(World.A_TURN_LEFT);
                        w.doAction(World.A_MOVE);
                    }
                    else if(w.getDirection() == World.DIR_DOWN)
                    {
                          System.out.println("45_022");
                           w.doAction(World.A_TURN_RIGHT);
                        w.doAction(World.A_MOVE);
                    }
                    else if(w.getDirection() == World.DIR_LEFT)
                    {
                          System.out.println("45_023");
                        w.doAction(World.A_MOVE);
                    }
                    else if(w.getDirection() == World.DIR_RIGHT)
                    {
                          System.out.println("45_024");
                           w.doAction(World.A_TURN_RIGHT);
                       w.doAction(World.A_TURN_RIGHT);
                        w.doAction(World.A_MOVE); 
                    }
                }
                else if(w.isVisited(cX-1, cY))
                {
                    if(w.getDirection() == World.DIR_UP)
                    {
                        System.out.println("45_025");
                        w.doAction(World.A_MOVE);
                    }
                    else if(w.getDirection() == World.DIR_DOWN)
                    {
                          System.out.println("45_026");
                           w.doAction(World.A_TURN_RIGHT);
                            w.doAction(World.A_TURN_RIGHT);
                        w.doAction(World.A_MOVE);
                    }
                    else if(w.getDirection() == World.DIR_LEFT)
                    {
                          System.out.println("45_027");
                           w.doAction(World.A_TURN_RIGHT);
                        w.doAction(World.A_MOVE);
                    }
                    else if(w.getDirection() == World.DIR_RIGHT)
                    {
                          System.out.println("45_028");
                           w.doAction(World.A_TURN_LEFT);
                        w.doAction(World.A_MOVE); 
                    }    
                }
                else
                {
                    if(w.getDirection() == World.DIR_DOWN || w.getDirection() == World.DIR_RIGHT)
                    {
                        System.out.println("45_029");
                           w.doAction(World.A_TURN_RIGHT);
                            w.doAction(World.A_TURN_RIGHT);
                        w.doAction(World.A_MOVE);
                    }
                    else
                    {
                       System.out.println("45_030");
                        w.doAction(World.A_MOVE); 
                    }
                }
            }
            
  // CASE 4 STENCH AND ONE empty tile VISited           
           
            else if(!w.hasStench(cX-1, cY-1) && w.isValidPosition(cX-1, cY-1) && w.isVisited(cX-1, cY-1) && move==0)
            {
                 System.out.println("!w.hasStench(cX-1, cY-1): extra condition check it");
                move=1;
 //CHECK ONCE              
                if(w.isVisited(cX-1, cY))
                {
                    if(w.getDirection() == World.DIR_UP)
                    {
                        System.out.println("45_031");
                        w.doAction(World.A_TURN_RIGHT);
                            w.doAction(World.A_TURN_RIGHT);
                        w.doAction(World.A_MOVE);
                    }
                    else if(w.getDirection() == World.DIR_DOWN)
                    {
                          System.out.println("45_032");
                        w.doAction(World.A_MOVE);
                    }
                    else if(w.getDirection() == World.DIR_LEFT)
                    {
                          System.out.println("45_033");
                           w.doAction(World.A_TURN_LEFT);
                        w.doAction(World.A_MOVE);
                    }
                    else if(w.getDirection() == World.DIR_RIGHT)
                    {
                          System.out.println("45_034");
                           w.doAction(World.A_TURN_RIGHT);
                        w.doAction(World.A_MOVE); 
                    }    
                    
                }
                else if(w.isVisited(cX, cY-1))
                {
                     if(w.getDirection() == World.DIR_UP)
                    {
                        System.out.println("45_035");
                        w.doAction(World.A_TURN_LEFT);
                        w.doAction(World.A_MOVE);
                    }
                    else if(w.getDirection() == World.DIR_DOWN)
                    {
                          System.out.println("45_036");
                           w.doAction(World.A_TURN_RIGHT);
                        w.doAction(World.A_MOVE);
                    }
                    else if(w.getDirection() == World.DIR_LEFT)
                    {
                          System.out.println("45_037");
                        w.doAction(World.A_MOVE);
                    }
                    else if(w.getDirection() == World.DIR_RIGHT)
                    {
                          System.out.println("45_038");
                          w.doAction(World.A_TURN_RIGHT);
                            w.doAction(World.A_TURN_RIGHT);
                        w.doAction(World.A_MOVE); 
                    }    
                }
                else
                {
                    if(w.getDirection() == World.DIR_RIGHT || w.getDirection() == World.DIR_UP)
                    {
                       System.out.println("45_039");
                        w.doAction(World.A_TURN_RIGHT);
                            w.doAction(World.A_TURN_RIGHT);
                            w.doAction(World.A_MOVE);
                    }
                    else
                    {
                        System.out.println("45_040");
                        w.doAction(World.A_MOVE);
                    }
                }
            } // END OF CASE 4 
            
         //   else if(!w.hasStench(cX-2, cY) && w.isValidPosition(cX-2, cY) && w.isVisited(cX-2, cY) && move==0)
          //  {
                
           // } 
            
 // no more cases
            
            else if(move==0)
              {   
                  move=1;
                  System.out.print(" !WHY! else case of STENCH");
                  System.out.println("counter c_stench value is= "+c_stench);
                  
                  if(c_stench == 2)
                  {
                      System.out.println("im in stench counter");
                      c_stench=0;
                      if(w.hasArrow())
                      {
                      w.doAction(World.A_SHOOT);
                      w.doAction(World.A_MOVE);
                      }
                      else{
                           System.out.println("!WARNING! ELSE CASE im in stench counter");
                          w.doAction(World.A_TURN_LEFT);
                       w.doAction(World.A_MOVE);
                      }
                  }
                  
                  else if(w.isVisited(cX-1, cY))
                      {
                        System.out.print("isVisited(cX-1, cY)");

                        if(w.getDirection() == World.DIR_RIGHT)
                        {
                          System.out.println("45: RIGHT");
                          w.doAction(World.A_TURN_LEFT);
                          w.doAction(World.A_TURN_LEFT);
                          w.doAction(World.A_MOVE);
                        }
                        else if(w.getDirection() == World.DIR_DOWN)
                        {
                          System.out.println("45_1: DOWN");
                          w.doAction(World.A_TURN_RIGHT);
                          w.doAction(World.A_MOVE);
                        }
                        else if(w.getDirection()== World.DIR_UP)
                        {
                             System.out.println("45_2: UP");
                          w.doAction(World.A_TURN_LEFT);
                          w.doAction(World.A_MOVE);

                        }
                        else if(w.getDirection() == World.DIR_LEFT)
                        {
                          System.out.println("45_3: LEFT");
                          w.doAction(World.A_MOVE);
                        }
                    }
                  
                  else
                      {
                          System.out.println("45_3.3: ELSE AGAIN");
                           w.doAction(World.A_TURN_LEFT);
                            w.doAction(World.A_TURN_LEFT);
                          w.doAction(World.A_MOVE);
                      }
                         
                  
 //ADD PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP
        /*         else           // left wall, changing position issue
                  {
                      System.out.println(" not Visited(cX-1, cY) else in else of STENCH # left wall, changing position issue");
// WUMPUS WHEN FIRST TWO TILES STENCH AND BREEZE
                      w.doAction(World.A_TURN_LEFT);
                   w.doAction(World.A_MOVE);
                   
                  }  
          */        
              
              } 
           
        } // close STENCH
 //-------------------------------------------END OF STENCH--------X-XXXX-X-X-X--X-X-X--X-X-X--X-X-X-X-X-X-X--X-X-X
 
 
 //----------------BREEZE---------BREEZE---------BREEZE---------BREEZE---------BREEZE---------BREEZE---------BREEZE---------BREEZE
        if(w.hasBreeze(cX, cY))
        {   
            if(w.hasPit(cX-1, cY) && move==0)
            {
                move=1;
                c_pit1+=1;
                System.out.println("c_pit1value is = "+c_pit1);
                if(c_pit1 >=2)
                {
                   c_pit1=0;
                    if(w.isUnknown(cX, cY+1) && w.isValidPosition(cX, cY+1))
                    {
                        System.out.println("PIT-1");
                        if(w.getDirection() == World.DIR_LEFT)
                        {
                            w.doAction(World.A_TURN_RIGHT);
                            w.doAction(World.A_MOVE);
                        }
                        else if(w.getDirection() == World.DIR_DOWN)
                        {
                            w.doAction(World.A_TURN_LEFT);
                            w.doAction(World.A_TURN_LEFT);
                            w.doAction(World.A_MOVE);
                        }
                        else if(w.getDirection() == World.DIR_RIGHT)
                        {
                             w.doAction(World.A_TURN_LEFT);
                            w.doAction(World.A_MOVE);
                        }
                        else if(w.getDirection() == World.DIR_UP)
                        {
                            w.doAction(World.A_MOVE);
                        }
                    }
                    else if(w.isUnknown(cX+1, cY))
                       {
                           System.out.println("PIT-2 : X+1,Y");
                           if(w.getDirection() == World.DIR_LEFT)
                        {
                           w.doAction(World.A_TURN_LEFT);
                            w.doAction(World.A_TURN_LEFT);
                            w.doAction(World.A_MOVE);
                        }
                        else if(w.getDirection() == World.DIR_DOWN)
                        {
                            w.doAction(World.A_TURN_LEFT);
                            w.doAction(World.A_MOVE);
                        }
                        else if(w.getDirection() == World.DIR_RIGHT)
                        {
                            w.doAction(World.A_MOVE);
                        }
                        else if(w.getDirection() == World.DIR_UP)
                        {
                           w.doAction(World.A_TURN_RIGHT);
                            w.doAction(World.A_MOVE);
                        }
                       }
                    else if(w.isUnknown(cX, cY-1) & w.isValidPosition(cX, cY-1))
                    {
                        System.out.println("PIT-3: X,Y-1");
                        if(w.getDirection() == World.DIR_LEFT)
                        {
                           w.doAction(World.A_TURN_LEFT);
                            w.doAction(World.A_MOVE);
                        }
                        else if(w.getDirection() == World.DIR_DOWN)
                        {
                            w.doAction(World.A_MOVE);
                        }
                        else if(w.getDirection() == World.DIR_RIGHT)
                        {
                           w.doAction(World.A_TURN_RIGHT);
                            w.doAction(World.A_MOVE);
                        }
                        else if(w.getDirection() == World.DIR_UP)
                        {
                           w.doAction(World.A_TURN_RIGHT);
                            w.doAction(World.A_TURN_RIGHT);
                            w.doAction(World.A_MOVE);
                        }      
                    }
                    else
                    {
                        System.out.println("PIT-4: ELSE");
                        
                        if(!w.isValidPosition(cX, cY-1) && (w.getDirection() == World.DIR_LEFT))
                        {
                             w.doAction(World.A_TURN_RIGHT);
                            w.doAction(World.A_MOVE);
                        }
                        
                        else if(w.getDirection() == World.DIR_LEFT)
                           {
                    System.out.println("EXTRA-01.1");
                        w.doAction(World.A_TURN_LEFT);
                        w.doAction(World.A_TURN_LEFT);
                        w.doAction(World.A_MOVE);
                             }
                           else 
                           {
                    System.out.println("EXTRA-02.1");
                    w.doAction(World.A_MOVE);
                             }
                    }
                    
                }  
              else
                {
                 if(w.getDirection() == World.DIR_LEFT)
                {
                    System.out.println("EXTRA-01");
                        w.doAction(World.A_TURN_LEFT);
                        w.doAction(World.A_TURN_LEFT);
                        w.doAction(World.A_MOVE);
                }
                else 
                {
                    System.out.println("EXTRA-02");
                    w.doAction(World.A_MOVE);
                }
                }
            }
 //EXTRA CONDITION CHANGE IT
            else if(w.hasPit(cX+1, cY) && move==0 )
            {
               if( w.isUnknown(cX, cY+1) && w.isValidPosition(cX, cY+1))
               {
                     if(w.getDirection() == World.DIR_LEFT)
                        {
                            w.doAction(World.A_TURN_RIGHT);
                            w.doAction(World.A_MOVE);
                        }
                        else if(w.getDirection() == World.DIR_DOWN)
                        {
                            w.doAction(World.A_TURN_LEFT);
                            w.doAction(World.A_TURN_LEFT);
                            w.doAction(World.A_MOVE);
                        }
                        else if(w.getDirection() == World.DIR_RIGHT)
                        {
                             w.doAction(World.A_TURN_LEFT);
                            w.doAction(World.A_MOVE);
                        }
                        else if(w.getDirection() == World.DIR_UP)
                        {
                            w.doAction(World.A_MOVE);
                        }  
               }
               else if(w.isUnknown(cX, cY-1) && w.isValidPosition(cX, cY-1))
               {
                    if(w.getDirection() == World.DIR_LEFT)
                        {
                             w.doAction(World.A_TURN_LEFT);
                            w.doAction(World.A_MOVE);
                        }
                        else if(w.getDirection() == World.DIR_DOWN)
                        {
                           
                            w.doAction(World.A_MOVE);
                        }
                        else if(w.getDirection() == World.DIR_RIGHT)
                        {
                             w.doAction(World.A_TURN_RIGHT);
                            w.doAction(World.A_MOVE);
                        }
                        else if(w.getDirection() == World.DIR_UP)
                        {
                             w.doAction(World.A_TURN_LEFT);
                            w.doAction(World.A_TURN_LEFT);
                            w.doAction(World.A_MOVE);
                        }  
               
               }
               else if(w.isUnknown(cX-1, cY) && w.isValidPosition(cX-1, cY))
               {
                   if(w.getDirection() == World.DIR_LEFT)
                        {
                             
                            w.doAction(World.A_MOVE);
                        }
                        else if(w.getDirection() == World.DIR_DOWN)
                        {
                            w.doAction(World.A_TURN_RIGHT);
                            w.doAction(World.A_MOVE);
                        }
                        else if(w.getDirection() == World.DIR_RIGHT)
                        {
                             w.doAction(World.A_TURN_RIGHT); 
                            w.doAction(World.A_TURN_RIGHT);
                            w.doAction(World.A_MOVE);
                        }
                        else if(w.getDirection() == World.DIR_UP)
                        {
                             w.doAction(World.A_TURN_LEFT);
     
                            w.doAction(World.A_MOVE);
                        }  
               }
               else
                {
                move=1;
                if(w.getDirection() == World.DIR_RIGHT)
                {
                System.out.println("EXTRA-03");
                        w.doAction(World.A_TURN_LEFT);
                        w.doAction(World.A_TURN_LEFT);
                        w.doAction(World.A_MOVE);
                }
                else
                {
                    System.out.println("EXTRA-04");
                    w.doAction(World.A_MOVE);
                }
                        }
            }
            else if(w.hasPit(cX, cY+1) && move==0)
            {
                move=1;
                if(w.getDirection() == World.DIR_UP)
                {
                    System.out.println("EXTRA-05");
                        w.doAction(World.A_TURN_LEFT);
                        w.doAction(World.A_TURN_LEFT);
                        w.doAction(World.A_MOVE);
                    
                }
                else
                {
                    // ADD CASE hasbreeze x-1,y-1
                    System.out.println("EXTRA-06");
                    w.doAction(World.A_MOVE);
                }
            }
            else if(w.hasPit(cX, cY-1) && move==0)
            {
                move=1;
                counter+=1;
                System.out.println("counter for pit"+counter);
                
            /*    if(w.hasBreeze(cX-1, cY-1) && w.isUnknown(cX-1, cY))
                {
                    System.out.println("1: unknown");
                    
                     if(w.getDirection() == World.DIR_DOWN)
                        {
                            w.doAction(World.A_TURN_RIGHT);
                            w.doAction(World.A_MOVE);
                        }
                        else if(w.getDirection() == World.DIR_LEFT)
                        {
                            w.doAction(World.A_MOVE);
                        }
                        else if(w.getDirection() == World.DIR_RIGHT)
                        {
                            w.doAction(World.A_TURN_LEFT);
                            w.doAction(World.A_TURN_LEFT);
                            w.doAction(World.A_MOVE);
                        }
                        else if(w.getDirection() == World.DIR_UP)
                        {
                            w.doAction(World.A_TURN_LEFT);
                            w.doAction(World.A_MOVE);
                        }
                }*/
            if(counter >=2)
                {
//REMOVE ADDITIONAL IF ELSE IN BELOW CONDITIONS                    
                    System.out.println("counter is "+counter);
                    counter=0;
                    
                     if(w.getDirection() == World.DIR_DOWN)
                        {
                            if(!w.isValidPosition(cX-1, cY))
                              
                            {w.doAction(World.A_TURN_LEFT);
                            w.doAction(World.A_MOVE);}
                            else
                            {
                            w.doAction(World.A_TURN_RIGHT);
                            w.doAction(World.A_MOVE);
                            }
                        }
                        else if(w.getDirection() == World.DIR_LEFT)
                        {
                             if(!w.isValidPosition(cX-1, cY))
                              
                            {w.doAction(World.A_TURN_LEFT);
                            w.doAction(World.A_MOVE);}
                            else
                            {
                            w.doAction(World.A_MOVE);
                            }
                        }
                        else if(w.getDirection() == World.DIR_RIGHT)
                        {
                             if(!w.isValidPosition(cX-1, cY))
                              
                            {
                            w.doAction(World.A_MOVE);}
                            else
                            {
                            w.doAction(World.A_TURN_LEFT);
                            w.doAction(World.A_TURN_LEFT);
                            w.doAction(World.A_MOVE);
                            }
                        }
                        else if(w.getDirection() == World.DIR_UP)
                        {
                             if(!w.isValidPosition(cX-1, cY))
                              
                            {w.doAction(World.A_TURN_RIGHT);
                            w.doAction(World.A_MOVE);}
                            else
                            {
                            w.doAction(World.A_TURN_LEFT);
                            w.doAction(World.A_MOVE);
                            }
                        }
                }
               
                else{
                if(w.getDirection() == World.DIR_DOWN)
                {
                     System.out.println("EXTRA-07");
                        w.doAction(World.A_TURN_LEFT);
                        w.doAction(World.A_TURN_LEFT);
                        w.doAction(World.A_MOVE);
                    
                }
                else
                {
                    System.out.println("EXTRA-08");
                    w.doAction(World.A_MOVE);
                }
                        }
            }
 
            
            
 //### BREEZE AND ONE empty tile VISited --SAFE MOVES   ---------------------  
 // CASE 1 BREEZE AND ONE empty tile VISited
            else if(!w.hasBreeze(cX+1, cY-1) && w.isValidPosition(cX+1, cY-1) && w.isVisited(cX+1, cY-1) && move==0 && (w.isUnknown(cX+1, cY) || w.isUnknown(cX, cY-1)))
            {
                move=1;
                
                if(w.isVisited(cX+1, cY) && w.isUnknown(cX, cY-1))
                {
                    if(w.getDirection() == World.DIR_LEFT)
                    {
                    System.out.println("45_001");
                    w.doAction(World.A_TURN_LEFT);
                    w.doAction(World.A_MOVE);
                    }
                    else if(w.getDirection() == World.DIR_DOWN)
                    {
                    System.out.println("45_002");
                    w.doAction(World.A_MOVE);
                    }   
                    else if(w.getDirection() == World.DIR_RIGHT)
                    {
                       System.out.println("45_003");
                       w.doAction(World.A_TURN_RIGHT);
                       w.doAction(World.A_MOVE);
                    }
                    else if(w.getDirection() == World.DIR_UP)
                    {
                       System.out.println("45_004");
                       w.doAction(World.A_TURN_RIGHT);
                       w.doAction(World.A_TURN_RIGHT);
                       w.doAction(World.A_MOVE); 
                    }
                }
                else if(w.isVisited(cX, cY-1) && w.isUnknown(cX+1, cY))
                {
                    if(w.getDirection() == World.DIR_LEFT)
                    {
                    System.out.println("45_005");
                     w.doAction(World.A_TURN_RIGHT);
                       w.doAction(World.A_TURN_RIGHT);
                    w.doAction(World.A_MOVE);
                    }
                    else if(w.getDirection() == World.DIR_DOWN)
                    {
                    System.out.println("45_006");
                    w.doAction(World.A_TURN_LEFT);
                    w.doAction(World.A_MOVE);
                    }   
                    else if(w.getDirection() == World.DIR_RIGHT)
                    {
                       System.out.println("45_007");
                       w.doAction(World.A_MOVE);
                    }
                    else if(w.getDirection() == World.DIR_UP)
                    {
                       System.out.println("45_008");
                       w.doAction(World.A_TURN_RIGHT);
                       w.doAction(World.A_MOVE); 
                    }
                }
                else
                {
                    System.out.println("45_008.1: ADD NEXT CASES");
                   /*f(w.getDirection() == World.DIR_UP|| w.getDirection() == World.DIR_LEFT)
                    {
                        System.out.println("45_009 MAY BE OTHER CASE");
                        w.doAction(World.A_TURN_RIGHT);
                       w.doAction(World.A_TURN_RIGHT);
                    w.doAction(World.A_MOVE);
                    
                    }
                    else
                    {
                        System.out.println("45_010 MAY BE OTHER CASE");
                        w.doAction(World.A_MOVE);
                    }*/
                }
                    
            }
// CASE 2 BREEZE AND ONE empty tile VISited           
            else if (!w.hasBreeze(cX+1, cY+1) && w.isValidPosition(cX+1, cY+1) && w.isVisited(cX+1, cY+1) && move==0 && (w.isUnknown(cX+1, cY) || w.isUnknown(cX, cY+1)))
            {
                move=1;
                if(w.isVisited(cX+1, cY) && w.isUnknown(cX, cY+1))
                {
                    if(w.getDirection() == World.DIR_UP)
                    {
                        System.out.println("45_011");
                        w.doAction(World.A_MOVE);
                    
                    }
                    else if(w.getDirection() == World.DIR_DOWN)
                    {
                          System.out.println("45_012");
                           w.doAction(World.A_TURN_RIGHT);
                       w.doAction(World.A_TURN_RIGHT);
                        w.doAction(World.A_MOVE);
                    }
                    else if(w.getDirection() == World.DIR_LEFT)
                    {
                          System.out.println("45_013");
                           w.doAction(World.A_TURN_RIGHT);
                        w.doAction(World.A_MOVE);
                    }
                    else if(w.getDirection() == World.DIR_RIGHT)
                    {
                          System.out.println("45_014");
                           w.doAction(World.A_TURN_LEFT);
                        w.doAction(World.A_MOVE);
                    }
                }
                else if(w.isVisited(cX, cY+1) && w.isUnknown(cX+1, cY))
                {
                    if(w.getDirection() == World.DIR_UP)
                    {
                        System.out.println("45_015");
                         w.doAction(World.A_TURN_RIGHT);
                        w.doAction(World.A_MOVE);
                    }
                    else if(w.getDirection() == World.DIR_DOWN)
                    {
                          System.out.println("45_016");
                           w.doAction(World.A_TURN_LEFT);
                        w.doAction(World.A_MOVE);
                    }
                    else if(w.getDirection() == World.DIR_LEFT)
                    {
                          System.out.println("45_017");
                           w.doAction(World.A_TURN_RIGHT);
                       w.doAction(World.A_TURN_RIGHT);
                        w.doAction(World.A_MOVE);
                    }
                    else if(w.getDirection() == World.DIR_RIGHT)
                    {
                          System.out.println("45_018");
                        w.doAction(World.A_MOVE);
                    
                    }
                }
                else
                {
                    System.out.println("45_018.1: ADD NEXT CASES");
                  /*if(w.getDirection() == World.DIR_LEFT || w.getDirection() == World.DIR_DOWN)
                    {
                        System.out.println("45_019");
                           w.doAction(World.A_TURN_RIGHT);
                       w.doAction(World.A_TURN_RIGHT);
                        w.doAction(World.A_MOVE);
                    }
                    else
                    {
                        System.out.println("45_020");
                        w.doAction(World.A_MOVE);    
                    }*/
                }
            }
// CASE 3 BREEZE AND ONE empty tile VISited            
            else if(!w.hasBreeze(cX-1, cY+1) && w.isVisited(cX-1, cY+1) && w.isVisited(cX-1, cY+1) && move==0 && (w.isUnknown(cX, cY+1) || w.isUnknown(cX-1, cY)))
            {
                move=1;
                if(w.isVisited(cX, cY+1) && w.isUnknown(cX-1, cY))
                {
                     if(w.getDirection() == World.DIR_UP)
                    {
                        System.out.println("45_021");
                         w.doAction(World.A_TURN_LEFT);
                        w.doAction(World.A_MOVE);
                    }
                    else if(w.getDirection() == World.DIR_DOWN)
                    {
                          System.out.println("45_022");
                           w.doAction(World.A_TURN_RIGHT);
                        w.doAction(World.A_MOVE);
                    }
                    else if(w.getDirection() == World.DIR_LEFT)
                    {
                          System.out.println("45_023");
                        w.doAction(World.A_MOVE);
                    }
                    else if(w.getDirection() == World.DIR_RIGHT)
                    {
                          System.out.println("45_024");
                           w.doAction(World.A_TURN_RIGHT);
                       w.doAction(World.A_TURN_RIGHT);
                        w.doAction(World.A_MOVE); 
                    }
                }
                else if(w.isVisited(cX-1, cY) && w.isUnknown(cX, cY+1))
                {
                    if(w.getDirection() == World.DIR_UP)
                    {
                        System.out.println("45_025");
                        w.doAction(World.A_MOVE);
                    }
                    else if(w.getDirection() == World.DIR_DOWN)
                    {
                          System.out.println("45_026");
                           w.doAction(World.A_TURN_RIGHT);
                            w.doAction(World.A_TURN_RIGHT);
                        w.doAction(World.A_MOVE);
                    }
                    else if(w.getDirection() == World.DIR_LEFT)
                    {
                          System.out.println("45_027");
                           w.doAction(World.A_TURN_RIGHT);
                        w.doAction(World.A_MOVE);
                    }
                    else if(w.getDirection() == World.DIR_RIGHT)
                    {
                          System.out.println("45_028");
                           w.doAction(World.A_TURN_LEFT);
                        w.doAction(World.A_MOVE); 
                    }    
                }
                else
                {
                    System.out.println("45_028.1: ADD NEXT LINES");
                  /*if(w.getDirection() == World.DIR_DOWN || w.getDirection() == World.DIR_RIGHT)
                    {
                        System.out.println("45_029");
                           w.doAction(World.A_TURN_RIGHT);
                            w.doAction(World.A_TURN_RIGHT);
                        w.doAction(World.A_MOVE);
                    }
                    else
                    {
                       System.out.println("45_030");
                        w.doAction(World.A_MOVE); 
                    } */
                }
            }
            
  // CASE 4 BREEZE AND ONE empty tile VISited           
           
            else if(!w.hasBreeze(cX-1, cY-1) && w.isValidPosition(cX-1, cY-1) && w.isVisited(cX-1, cY-1) && move==0 && (w.isUnknown(cX-1, cY) || w.isUnknown(cX, cY-1)))
            {
                move=1;
 //CHECK ONCE              
                if(w.isVisited(cX-1, cY) && w.isUnknown(cX, cY-1))
                {
                    if(w.getDirection() == World.DIR_UP)
                    {
                        System.out.println("45_031");
                        w.doAction(World.A_TURN_RIGHT);
                            w.doAction(World.A_TURN_RIGHT);
                        w.doAction(World.A_MOVE);
                    }
                    else if(w.getDirection() == World.DIR_DOWN)
                    {
                          System.out.println("45_032");
                        w.doAction(World.A_MOVE);
                    }
                    else if(w.getDirection() == World.DIR_LEFT)
                    {
                          System.out.println("45_033");
                           w.doAction(World.A_TURN_LEFT);
                        w.doAction(World.A_MOVE);
                    }
                    else if(w.getDirection() == World.DIR_RIGHT)
                    {
                          System.out.println("45_034");
                           w.doAction(World.A_TURN_RIGHT);
                        w.doAction(World.A_MOVE); 
                    }    
                    
                }
                else if(w.isVisited(cX, cY-1) && w.isUnknown(cX-1, cY))
                {
                     if(w.getDirection() == World.DIR_UP)
                    {
                        System.out.println("45_035");
                        w.doAction(World.A_TURN_LEFT);
                        w.doAction(World.A_MOVE);
                    }
                    else if(w.getDirection() == World.DIR_DOWN)
                    {
                          System.out.println("45_036");
                           w.doAction(World.A_TURN_RIGHT);
                        w.doAction(World.A_MOVE);
                    }
                    else if(w.getDirection() == World.DIR_LEFT)
                    {
                          System.out.println("45_037");
                        w.doAction(World.A_MOVE);
                    }
                    else if(w.getDirection() == World.DIR_RIGHT)
                    {
                          System.out.println("45_038");
                          w.doAction(World.A_TURN_RIGHT);
                            w.doAction(World.A_TURN_RIGHT);
                        w.doAction(World.A_MOVE); 
                    }    
                }
                else
                {
                    System.out.println("45_038: ADD NEXT LINES");
                    /*
                    if(w.getDirection() == World.DIR_RIGHT || w.getDirection() == World.DIR_UP)
                    {
                       System.out.println("45_039");
                        w.doAction(World.A_TURN_RIGHT);
                            w.doAction(World.A_TURN_RIGHT);
                            w.doAction(World.A_MOVE);
                    }
                    else
                    {
                        System.out.println("45_040");
                        w.doAction(World.A_MOVE);
                    }*/
                }
            } // END OF CASE 4 
        
 // TWO BREEZES           
            else if(w.hasBreeze(cX-1, cY+1) && move==0)
            {
                
                counter3+=1;
                counter3_1+=1;
                System.out.println("COunter value here is "+counter3+ "AND" +counter3_1);
                System.out.println("45_4..CASE BREEZE X-1,Y+1");
             
                       if(counter3>=2)
               {
                   
                   counter3=0;
                  if(w.isUnknown(cX+1, cY) && w.isValidPosition(cX+1, cY))
                 {
                     move=1;
                    if(w.getDirection() == World.DIR_RIGHT)
                    {
                        System.out.println("RISK 1");
                        w.doAction(World.A_MOVE);
                    }
                    else if(w.getDirection() == World.DIR_DOWN)
                    {
                    System.out.println("RISK 2");
                    w.doAction(World.A_TURN_LEFT);
                        w.doAction(World.A_MOVE);
                    }
                    else if(w.getDirection() == World.DIR_LEFT)
                    {
                    System.out.println("RISK 3");
                    w.doAction(World.A_TURN_RIGHT);
                            w.doAction(World.A_TURN_RIGHT);
                        w.doAction(World.A_MOVE);
                    }
                    else if(w.getDirection() == World.DIR_UP)
                    {
                    System.out.println("RISK 4");
                    w.doAction(World.A_TURN_RIGHT);
                        w.doAction(World.A_MOVE);
                    }
                 }
                  else if(w.isUnknown(cX, cY-1) && w.isValidPosition(cX, cY-1))
                  {
                      move=1;
                      if(w.getDirection() == World.DIR_RIGHT)
                    {
                        System.out.println("RISK 1.1");
                        w.doAction(World.A_TURN_RIGHT);
                        w.doAction(World.A_MOVE);
                    }
                    else if(w.getDirection() == World.DIR_DOWN)
                    {
                    System.out.println("RISK 2.1");
                        w.doAction(World.A_MOVE);
                    }
                    else if(w.getDirection() == World.DIR_LEFT)
                    {
                    System.out.println("RISK 3.1");
                    w.doAction(World.A_TURN_LEFT);
                        w.doAction(World.A_MOVE);
                    }
                    else if(w.getDirection() == World.DIR_UP)
                    {
                    System.out.println("RISK 4.1");
                    w.doAction(World.A_TURN_RIGHT);
                    w.doAction(World.A_TURN_RIGHT);
                        w.doAction(World.A_MOVE);
                    }
                      
                  }
                  else
                  {
                      System.out.println("NOTHING1");
                  }
               }
                       
                       else if(counter3_1>=3 && move==0)
                   {
                       counter3_1=0;
                       
                       if(w.isUnknown(cX, cY+1) && w.isValidPosition(cX, cY+1))
                       {
                            move=1;
                           System.out.println(" unknown x,y+1");
                           if(w.getDirection() == World.DIR_RIGHT)
                           {
                               w.doAction(World.A_TURN_LEFT);
                               w.doAction(World.A_MOVE);
                           }
                           else if(w.getDirection() == World.DIR_DOWN)
                           {
                              w.doAction(World.A_TURN_LEFT);
                               w.doAction(World.A_TURN_LEFT);
                               w.doAction(World.A_MOVE);
                           }
                           else if(w.getDirection() == World.DIR_LEFT)
                           {
                               w.doAction(World.A_TURN_RIGHT);
                               w.doAction(World.A_MOVE);
                           }
                           else if(w.getDirection() == World.DIR_UP)
                           {
                               w.doAction(World.A_MOVE);
                           }
                       }
                       else if(w.isUnknown(cX-1, cY) && w.isValidPosition(cX-1, cY))
                               {
                                    move=1;
                                   System.out.println("unknown x-1,y");
                                   if(w.getDirection() == World.DIR_RIGHT)
                           {
                               w.doAction(World.A_TURN_LEFT);
                                w.doAction(World.A_TURN_LEFT);
                               w.doAction(World.A_MOVE);
                           }
                           else if(w.getDirection() == World.DIR_DOWN)
                           {
                             
                               w.doAction(World.A_TURN_RIGHT);
                               w.doAction(World.A_MOVE);
                           }
                           else if(w.getDirection() == World.DIR_LEFT)
                           {
                               w.doAction(World.A_MOVE);
                           }
                           else if(w.getDirection() == World.DIR_UP)
                           {
                                w.doAction(World.A_TURN_LEFT);
                               w.doAction(World.A_MOVE);
                           }
                                   
                               }
                   }
                   
                       else if(move==0)
                       {
                            move=1;
                if(w.getDirection() == World.DIR_DOWN)
                {
                    if(w.isVisited(cX, cY+1) && !w.hasPit(cX, cY+1))
                    {
                        System.out.println("45_5");
                        w.doAction(World.A_TURN_RIGHT);
                        w.doAction(World.A_TURN_RIGHT);
                        w.doAction(World.A_MOVE);
                    }
                    else if(w.isVisited(cX-1, cY) && !w.hasPit(cX-1, cY))
                    {
                        System.out.println("45_6");
                        w.doAction(World.A_TURN_RIGHT);
                        w.doAction(World.A_MOVE);
                    }
                    else
                    {
                        System.out.println("45_7, NOTHING");
                    }
                    
                }
                else if(w.getDirection() == World.DIR_LEFT)
                {
                    if(w.isVisited(cX-1, cY) && !w.hasPit(cX-1, cY))
                    {
                       System.out.println("45_8");
                        w.doAction(World.A_MOVE);
                    }
                    else if(w.isVisited(cX, cY+1) && !w.hasPit(cX, cY+1))
                    {
                        System.out.println("45_9");
                        w.doAction(World.A_TURN_RIGHT);
                        w.doAction(World.A_MOVE);
                    }
                    else
                    {
                        System.out.println("45_10");
                        w.doAction(World.A_TURN_RIGHT);
                        w.doAction(World.A_TURN_RIGHT);
                        w.doAction(World.A_MOVE);  
                    }
                    
                }
                else if(w.getDirection() == World.DIR_RIGHT)
                {
                    if(w.isVisited(cX-1, cY) && !w.hasPit(cX-1, cY))
                    {
                        System.out.println("45_11");
                        w.doAction(World.A_TURN_RIGHT);
                        w.doAction(World.A_TURN_RIGHT);
                        w.doAction(World.A_MOVE); 
                    }
                    else if(w.isVisited(cX, cY+1) && !w.hasPit(cX, cY+1))
                    {
                        System.out.println("45_12");
                        w.doAction(World.A_TURN_LEFT);
                        w.doAction(World.A_MOVE);
                    }
                    else
                    {
                     System.out.println("45_13, NOTHING");   
                    }
                }
                else if(w.getDirection() == World.DIR_UP)
                {
                    if(w.isVisited(cX-1, cY) && !w.hasPit(cX-1, cY))
                    {
                        System.out.println("45_14");
                        w.doAction(World.A_TURN_LEFT);
                        w.doAction(World.A_MOVE);
                    }
                    else if(w.isVisited(cX, cY+1) && !w.hasPit(cX, cY+1))
                    {
                        System.out.println("45_15");
                        w.doAction(World.A_MOVE);
                    }
                    else
                    {
                        System.out.println("45_16");
                        w.doAction(World.A_TURN_RIGHT);
                        w.doAction(World.A_TURN_RIGHT);
                        w.doAction(World.A_MOVE);
                    }
                }
                else
                   System.out.println("else..NOTHING");
                      
                       }
            }// END OF BREEZE X-1,Y+1
           
            else if(w.hasBreeze(cX+1, cY-1) && move==0)
            {
                
                c_B2+=1;
                c_B2_1+=1;
                System.out.println("hasBreeze(cX+1, cY-1)");
                 System.out.println("c_B2 counter values are "+c_B2+" and "+c_B2_1);
                      if(c_B2 >=2)
                {
                    c_B2=0;
                    if(w.isUnknown(cX, cY+1) && move==0 && w.isValidPosition(cX, cY+1))
                    {
                        move=1;
                         if(w.getDirection() == World.DIR_UP)
                             {
                                System.out.println("B2.9");
                                w.doAction(World.A_MOVE);
                            }
                              else if(w.getDirection() == World.DIR_DOWN)
                             {
                                    System.out.println("B2.10");
                                w.doAction(World.A_TURN_RIGHT);
                                 w.doAction(World.A_TURN_RIGHT);
                                 w.doAction(World.A_MOVE);
                             }
                            else if(w.getDirection() == World.DIR_LEFT)
                            {
                                System.out.println("B2.11");
                                 w.doAction(World.A_TURN_RIGHT);
                                 w.doAction(World.A_MOVE);
                            }
                            else if(w.getDirection() == World.DIR_RIGHT)
                            {
                                System.out.println("B2.12");
                                 w.doAction(World.A_TURN_LEFT);
                                w.doAction(World.A_MOVE); 
                             }    
                        
                    }
                    else if(w.isUnknown(cX-1, cY) && move==0 && w.isValidPosition(cX-1, cY))
                    {
                        move=1;
                         if(w.getDirection() == World.DIR_UP)
                             {
                                System.out.println("B2.13");
                                w.doAction(World.A_TURN_LEFT);
                                w.doAction(World.A_MOVE);
                            }
                              else if(w.getDirection() == World.DIR_DOWN)
                             {
                                    System.out.println("B2.14");
                                 w.doAction(World.A_TURN_RIGHT);
                                 w.doAction(World.A_MOVE);
                             }
                            else if(w.getDirection() == World.DIR_LEFT)
                            {
                                System.out.println("B2.15");
                                 w.doAction(World.A_MOVE);
                            }
                            else if(w.getDirection() == World.DIR_RIGHT)
                            {
                                System.out.println("B2.16");
                                w.doAction(World.A_TURN_LEFT);
                                 w.doAction(World.A_TURN_LEFT);
                                w.doAction(World.A_MOVE); 
                             } 
                    }
                
                }
                
                   else if(c_B2_1 >= 3 && move==0)
                    {
                       
                        c_B2_1 =0;
                        if(w.isUnknown(cX+1, cY) && w.isValidPosition(cX+1, cY))
                        {
                             move=1;
                            if(w.getDirection() == World.DIR_UP)
                             {
                                System.out.println("B2.1");
                                w.doAction(World.A_TURN_RIGHT);
                                w.doAction(World.A_MOVE);
                            }
                              else if(w.getDirection() == World.DIR_DOWN)
                             {
                                    System.out.println("B2.2");
                                    w.doAction(World.A_TURN_LEFT);
                                 w.doAction(World.A_MOVE);
                             }
                            else if(w.getDirection() == World.DIR_LEFT)
                            {
                                System.out.println("B2.3");
                                 w.doAction(World.A_TURN_LEFT);
                                 w.doAction(World.A_TURN_LEFT);
                                 w.doAction(World.A_MOVE);
                            }
                            else if(w.getDirection() == World.DIR_RIGHT)
                            {
                                System.out.println("B2.4");
                                w.doAction(World.A_MOVE); 
                             }    
                        
                        }
                        else if(w.isUnknown(cX, cY-1) && w.isValidPosition(cX, cY-1))
                        {
                             move=1;
                            if(w.getDirection() == World.DIR_UP)
                             {
                                System.out.println("B2.5");
                                w.doAction(World.A_TURN_RIGHT);
                                 w.doAction(World.A_TURN_RIGHT);
                                w.doAction(World.A_MOVE);
                            }
                              else if(w.getDirection() == World.DIR_DOWN)
                             {
                                    System.out.println("B2.6");
                                 w.doAction(World.A_MOVE);
                             }
                            else if(w.getDirection() == World.DIR_LEFT)
                            {
                                System.out.println("B2.7");
                                 w.doAction(World.A_TURN_LEFT);
                                 w.doAction(World.A_MOVE);
                            }
                            else if(w.getDirection() == World.DIR_RIGHT)
                            {
                                System.out.println("B2.8");
                                 w.doAction(World.A_TURN_RIGHT);
                                w.doAction(World.A_MOVE); 
                             }    
                        }
                    }
                   else if(move==0)
                {
                System.out.println("45_17..CASE 2 BREEZE X+1,Y+1");
                if(w.getDirection() == World.DIR_DOWN)
                {
                    if(w.isVisited(cX+1, cY))
                    {
                        System.out.println("45_18");
                        w.doAction(World.A_TURN_LEFT);
                        w.doAction(World.A_MOVE);
                    }
                    else if(w.isVisited(cX, cY-1))
                    {
                        System.out.println("45_19");
                        w.doAction(World.A_MOVE);
                    }
                    else
                    {
                        System.out.println("45_20");
                        w.doAction(World.A_TURN_RIGHT);
                        w.doAction(World.A_TURN_RIGHT);
                        w.doAction(World.A_MOVE);
                    }
                }
                else if(w.getDirection() == World.DIR_LEFT)
                {
                    if(w.isVisited(cX+1, cY))
                    {
                        System.out.println("45_21");
                        w.doAction(World.A_TURN_RIGHT);
                        w.doAction(World.A_TURN_RIGHT);
                        w.doAction(World.A_MOVE);      
                    }
                    else if(w.isVisited(cX, cY-1))
                    {
                        System.out.println("45_22");
                        w.doAction(World.A_TURN_LEFT);
                        w.doAction(World.A_MOVE);
                    }
                    else
                    {
                        System.out.println("45_23, NOTHING"); 
                    }
                }
                else if(w.getDirection() == World.DIR_RIGHT)
                {
                    if(w.isVisited(cX+1, cY))
                    {
                        System.out.println("45_24");
                        w.doAction(World.A_MOVE);
                    }
                    else if(w.isVisited(cX, cY-1))
                    {
                        System.out.println("45_25");
                        w.doAction(World.A_TURN_RIGHT);
                        w.doAction(World.A_MOVE);        
                    }
                    else
                    {
                        System.out.println("45_26");
                        w.doAction(World.A_TURN_RIGHT);
                        w.doAction(World.A_TURN_RIGHT);
                        w.doAction(World.A_MOVE);     
                    }
                    
                }
                else if(w.getDirection() == World.DIR_UP)
                {
                    if(w.isVisited(cX+1, cY))
                    {
                        System.out.println("45_27");
                        w.doAction(World.A_TURN_RIGHT);
                        w.doAction(World.A_MOVE);
                    }
                    else if(w.isVisited(cX, cY-1))
                    {
                         System.out.println("45_28");
                        w.doAction(World.A_TURN_RIGHT);
                        w.doAction(World.A_TURN_RIGHT);
                        w.doAction(World.A_MOVE);     
                    }
                    else
                    {
                        System.out.println("45_29");
                        w.doAction(World.A_MOVE);
                    }
                }
                else
                   System.out.println("else nothing Breeze x+1,Y-1");
                }
            } // END OF BREEZE X+1,Y-1
            
            else if(w.hasBreeze(cX-1, cY-1) && move==0)
            {
                c_B1+=1;
                c_B1_1+=1;
                System.out.println(".hasBreeze(cX-1, cY-1)");
                 System.out.println("c_B1 counter values are "+c_B1+" and "+c_B1_1);
                 if(c_B1 >=2)
                {
                    c_B1=0;
                       if(w.isUnknown(cX+1, cY) && w.isValidPosition(cX+1, cY))
                       {
                           move=1;
                        if(w.getDirection() == World.DIR_RIGHT)
                        {
                            w.doAction(World.A_MOVE);
                        }
                        else if(w.getDirection() == World.DIR_DOWN)
                        {
                            w.doAction(World.A_TURN_LEFT);
                            w.doAction(World.A_MOVE);
                        }
                        else if(w.getDirection() == World.DIR_LEFT)
                        {
                            w.doAction(World.A_TURN_LEFT);
                            w.doAction(World.A_TURN_LEFT);
                            w.doAction(World.A_MOVE);          
                        }
                        else if(w.getDirection()== World.DIR_UP)
                        {
                             w.doAction(World.A_TURN_RIGHT);
                            w.doAction(World.A_MOVE);
                        }
                       }
                        else if(w.isUnknown(cX, cY+1) && w.isValidPosition(cX, cY+1))
                          {
                              
                            move=1;
                               if(w.getDirection() == World.DIR_RIGHT)
                        {
                            w.doAction(World.A_TURN_LEFT);
                            w.doAction(World.A_MOVE);
                        }
                        else if(w.getDirection() == World.DIR_DOWN)
                        {
                            w.doAction(World.A_TURN_LEFT);
                             w.doAction(World.A_TURN_LEFT);
                            w.doAction(World.A_MOVE);
                        }
                        else if(w.getDirection() == World.DIR_LEFT)
                        {
                            w.doAction(World.A_TURN_RIGHT);
                            w.doAction(World.A_MOVE);          
                        }
                        else if(w.getDirection()== World.DIR_UP)
                        {
                            w.doAction(World.A_MOVE);
                        }
                              
                          }
                    
                }
               
                    if(c_B1_1 >= 4)
                    {
                    c_B1_1=0;
                    
                    if(w.isUnknown(cX-1, cY) && w.isValidPosition(cX-1, cY))
                    {
                        move=1;
                      System.out.println("hasPit(cX, cY-1)");   
                        if(w.getDirection() == World.DIR_DOWN)
                        {
                            w.doAction(World.A_TURN_RIGHT);
                            w.doAction(World.A_MOVE);
                        }
                        else if(w.getDirection() == World.DIR_LEFT)
                        {
                            w.doAction(World.A_MOVE);
                        }
                        else if(w.getDirection() == World.DIR_RIGHT)
                        {
                            w.doAction(World.A_TURN_LEFT);
                            w.doAction(World.A_TURN_LEFT);
                            w.doAction(World.A_MOVE);
                        }
                        else if(w.getDirection() == World.DIR_UP)
                        {
                            w.doAction(World.A_TURN_LEFT);
                            w.doAction(World.A_MOVE);
                        }
                    }
                    else if(w.isUnknown(cX, cY-1) && w.isValidPosition(cX, cY-1))
                    {
                        move=1;
                        if(w.getDirection() == World.DIR_DOWN)
                        {
                            
                            w.doAction(World.A_MOVE);
                        }
                        else if(w.getDirection() == World.DIR_LEFT)
                        {
                            w.doAction(World.A_TURN_LEFT);
                            w.doAction(World.A_MOVE);
                        }
                        else if(w.getDirection() == World.DIR_RIGHT)
                        {
                           
                            w.doAction(World.A_TURN_RIGHT);
                            w.doAction(World.A_MOVE);
                        }
                        else if(w.getDirection() == World.DIR_UP)
                        {
                            w.doAction(World.A_TURN_LEFT);
                            w.doAction(World.A_TURN_LEFT);
                            w.doAction(World.A_MOVE);
                        }
                    }
                }
                    
                    else if(move==0)
                    
                {
                if(w.getDirection() == World.DIR_DOWN)
                {
                    if(w.isVisited(cX-1, cY) )
                    {
                        System.out.println("45_30");
                        w.doAction(World.A_TURN_RIGHT);
                        w.doAction(World.A_MOVE); 
                    }
                    else if(w.isVisited(cX, cY-1))
                    {
                        System.out.println("45_31");
                        w.doAction(World.A_MOVE);   
                    }
                    else
                    {
                         System.out.println("45_32");
                        w.doAction(World.A_TURN_RIGHT);
                        w.doAction(World.A_TURN_RIGHT);
                        w.doAction(World.A_MOVE);    
                    }
                }
                else if(w.getDirection() == World.DIR_LEFT)
                {
                    if(w.isVisited(cX-1, cY))
                    {
                        System.out.println("45_33");
                        w.doAction(World.A_MOVE); 
                    }
                    else if(w.isVisited(cX, cY-1))
                    {
                        System.out.println("45_34");
                        w.doAction(World.A_TURN_LEFT);
                        w.doAction(World.A_MOVE);   
                    }
                    else
                    {
                        System.out.println("45_35");
                        w.doAction(World.A_TURN_RIGHT);
                        w.doAction(World.A_TURN_RIGHT);
                        w.doAction(World.A_MOVE);
                        
                    }
                }
                else if(w.getDirection() == World.DIR_RIGHT)
                {
                   if(w.isVisited(cX-1, cY))
                   {
                       System.out.println("45_36");
                        w.doAction(World.A_TURN_RIGHT);
                        w.doAction(World.A_TURN_RIGHT);
                        w.doAction(World.A_MOVE);
                   }
                   else if(w.isVisited(cX, cY-1))
                   {
                       System.out.println("45_37");
                        w.doAction(World.A_TURN_RIGHT);
                        w.doAction(World.A_MOVE);
                   }
                   else
                   {
                        System.out.println("45_38");
                        w.doAction(World.A_MOVE);
                   }
                }
                else if(w.getDirection() == World.DIR_UP)
                {
                    if(w.isVisited(cX-1, cY))
                    {
                        System.out.println("45_39");
                        w.doAction(World.A_TURN_LEFT);
                        w.doAction(World.A_MOVE);
                    }
                    else if(w.isVisited(cX, cY-1))
                    {
                        System.out.println("45_40");
                        w.doAction(World.A_TURN_RIGHT);
                        w.doAction(World.A_TURN_RIGHT);
                        w.doAction(World.A_MOVE);
                    }
                    else
                    {
                        System.out.println("45_41");
                        w.doAction(World.A_MOVE);
                    }
                }
                else
                   System.out.println("else");
            }
            }
//CASE 4 : TWO BREEZES
            else if(w.hasBreeze(cX+1, cY+1) && move==0)
            {
                c_B3+=1;
                 c_B3_1+=1;
                 System.out.println(".hasBreeze(cX+1, cY+1)");
                 System.out.println("c_B3 counter values are "+c_B3+" and "+c_B3_1);
                 if(c_B3>=2)
                {
                    c_B3=0;
                    if(w.isUnknown(cX-1, cY) && w.isValidPosition(cX-1, cY))
                    {
                        move=1;
                         if(w.getDirection() == World.DIR_UP)
                             {
                                System.out.println("B2.38");
                                 w.doAction(World.A_TURN_LEFT); 
                                w.doAction(World.A_MOVE);
                            }
                              else if(w.getDirection() == World.DIR_DOWN)
                             {
                                    System.out.println("B2.39");
                                    
                                w.doAction(World.A_TURN_RIGHT);
                                 w.doAction(World.A_MOVE);
                             }
                            else if(w.getDirection() == World.DIR_LEFT)
                            {
                                System.out.println("B2.40");
                                 w.doAction(World.A_MOVE);
                            }
                            else if(w.getDirection() == World.DIR_RIGHT)
                            {
                                System.out.println("B2.41");
                                   w.doAction(World.A_TURN_RIGHT); 
                                 w.doAction(World.A_TURN_RIGHT);
                                w.doAction(World.A_MOVE); 
                             }    
                    }
                    else if(w.isUnknown(cX, cY-1) && w.isValidPosition(cX, cY-1))
                    {
                        move=1;
                        if(w.getDirection() == World.DIR_UP)
                             {
                                System.out.println("B2.42");
                                 w.doAction(World.A_TURN_LEFT); 
                                  w.doAction(World.A_TURN_LEFT); 
                                w.doAction(World.A_MOVE);
                            }
                              else if(w.getDirection() == World.DIR_DOWN)
                             {
                                    System.out.println("B2.43");
                                 w.doAction(World.A_MOVE);
                             }
                            else if(w.getDirection() == World.DIR_LEFT)
                            {
                                System.out.println("B2.44");
                                 w.doAction(World.A_TURN_LEFT); 
                                 w.doAction(World.A_MOVE);
                            }
                            else if(w.getDirection() == World.DIR_RIGHT)
                            {
                                System.out.println("B2.45");
                                 w.doAction(World.A_TURN_RIGHT);
                                w.doAction(World.A_MOVE); 
                             }    
                    }
                }
               
                   else if(c_B3_1>=3 && move==0)
                   {
                        c_B3_1=0;
                        if(w.isUnknown(cX, cY+1) && w.isValidPosition(cX, cY+1))
                        {
                            move=1;
                             if(w.getDirection() == World.DIR_UP)
                             {
                                System.out.println("B2.30");
                                w.doAction(World.A_MOVE);
                            }
                              else if(w.getDirection() == World.DIR_DOWN)
                             {
                                    System.out.println("B2.31");
                                   w.doAction(World.A_TURN_RIGHT); 
                                 w.doAction(World.A_TURN_RIGHT);
                                 w.doAction(World.A_MOVE);
                             }
                            else if(w.getDirection() == World.DIR_LEFT)
                            {
                                System.out.println("B2.32");
                                w.doAction(World.A_TURN_RIGHT);
                                 w.doAction(World.A_MOVE);
                            }
                            else if(w.getDirection() == World.DIR_RIGHT)
                            {
                                System.out.println("B2.33");
                                w.doAction(World.A_TURN_LEFT);
                                w.doAction(World.A_MOVE); 
                             }    
                        
                        
                        }
                        else if(w.isUnknown(cX+1, cY) && w.isValidPosition(cX+1, cY))
                        {
                            move=1;
                             if(w.getDirection() == World.DIR_UP)
                             {
                                System.out.println("B2.34");
                                 w.doAction(World.A_TURN_RIGHT); 
                                w.doAction(World.A_MOVE);
                            }
                              else if(w.getDirection() == World.DIR_DOWN)
                             {
                                    System.out.println("B2.35");
                                    
                                w.doAction(World.A_TURN_LEFT);
                                 w.doAction(World.A_MOVE);
                             }
                            else if(w.getDirection() == World.DIR_LEFT)
                            {
                                System.out.println("B2.36");
                                   w.doAction(World.A_TURN_RIGHT); 
                                 w.doAction(World.A_TURN_RIGHT);
                                 w.doAction(World.A_MOVE);
                            }
                            else if(w.getDirection() == World.DIR_RIGHT)
                            {
                                System.out.println("B2.37");
                                w.doAction(World.A_MOVE); 
                             }    
                        }
                    }
                   else if(move==0)
                {
                    move=1;
                if(w.getDirection() == World.DIR_DOWN)
                {
                  if(w.isVisited(cX, cY+1))
                  {
                        System.out.println("45_42");
                        w.doAction(World.A_TURN_RIGHT);
                        w.doAction(World.A_TURN_RIGHT);
                        w.doAction(World.A_MOVE);  
                  }
                  else if(w.isVisited(cX+1, cY))
                  {
                       System.out.println("45_43");
                        w.doAction(World.A_TURN_LEFT);
                        w.doAction(World.A_MOVE);  
                  }
                  else
                  {
                      System.out.println("45_44");
                        w.doAction(World.A_MOVE);
                  }
                }
                else if(w.getDirection() == World.DIR_LEFT)
                {
                    if(w.isVisited(cX+1, cY))
                    {
                        System.out.println("45_45");
                        w.doAction(World.A_TURN_RIGHT);
                        w.doAction(World.A_TURN_RIGHT);
                        w.doAction(World.A_MOVE);
                    }
                    else if(w.isVisited(cX, cY+1))
                    {
                        System.out.println("45_46");
                        w.doAction(World.A_TURN_RIGHT);
                        w.doAction(World.A_MOVE);
                    }
                    else
                    {
                        System.out.println("45_47");
                        w.doAction(World.A_MOVE);
                    }
                }
                else if(w.getDirection() == World.DIR_RIGHT)
                {
                    if(w.isVisited(cX+1, cY))
                    {
                        System.out.println("45_48");
                        w.doAction(World.A_MOVE);
                    }
                    else if(w.isVisited(cX, cY+1))
                    {
                        System.out.println("45_49");
                        w.doAction(World.A_TURN_LEFT);
                        w.doAction(World.A_MOVE);
                    }
                    else
                    {
                       System.out.println("45_50");
                        w.doAction(World.A_TURN_RIGHT);
                        w.doAction(World.A_TURN_RIGHT);
                        w.doAction(World.A_MOVE); 
                    }
                }
                else if(w.getDirection() == World.DIR_UP)
                {
                    if(w.isVisited(cX+1, cY))
                    {
                        System.out.println("45_51");
                        w.doAction(World.A_TURN_RIGHT);
                        w.doAction(World.A_MOVE);
                    }
                    else if(w.isVisited(cX, cY+1))
                    {
                        System.out.println("45_52");
                        w.doAction(World.A_MOVE);
                    }
                    else
                    {
                        System.out.println("45_53");
                        w.doAction(World.A_TURN_RIGHT);
                        w.doAction(World.A_TURN_RIGHT);
                        w.doAction(World.A_MOVE); 
                    }
                }
                else
                   System.out.println("else");
                }
            } // END CASE 4 X+1,Y+1
            
            
            else if(move==0)
            {
                move=1;
                        
                if(!w.isValidPosition(cX-1, cY) && !w.isValidPosition(cX, cY-1) && w.isUnknown(cX+1, cY) && w.isUnknown(cX, cY+1))
                {
                     System.out.println("45_53.1--------- BREEZE IN FIRST TILE");
                     w.doAction(World.A_TURN_LEFT);  // extra case
                     w.doAction(World.A_MOVE);
                }
                else
                {
                        c_else+=1;
                   /* if(w.getDirection() == World.DIR_UP && w.isVisited(cX-1, cY))
                    {
                    System.out.println("45_53.2");
                         w.doAction(World.A_TURN_LEFT);
                        w.doAction(World.A_MOVE);
                    }*/
                    
                   // if(c_else>=3)
                   // {
                   //     c_else=0;
        System.out.println("LATEST CASE");
                       
                            if(w.isVisited(cX, cY+1) && !w.hasPit(cX, cY+1) && ((w.getDirection() == World.DIR_LEFT) || (w.getDirection() == World.DIR_RIGHT)))
                            {
                                 if(w.getDirection() == World.DIR_LEFT)
                                    {
                                        
                                    w.doAction(World.A_TURN_RIGHT);
                                        w.doAction(World.A_MOVE);
                                    }
                                 else if(w.getDirection() == World.DIR_RIGHT)
                                  {
                                      
                                        w.doAction(World.A_TURN_LEFT);
                                    w.doAction(World.A_MOVE);
                                  }
                            }
                         
                           else if(w.isVisited(cX+1, cY) && !w.hasPit(cX+1, cY) && (( w.getDirection() == World.DIR_DOWN) || (w.getDirection() == World.DIR_UP) ))
                            {
                                    if( w.getDirection() == World.DIR_DOWN)
                                    {
                                      w.doAction(World.A_TURN_LEFT);
                                        w.doAction(World.A_MOVE);
                                     }
                                    else if(w.getDirection() == World.DIR_UP)
                                    {
                                         w.doAction(World.A_TURN_RIGHT);
                                        w.doAction(World.A_MOVE);
                                    }
                            }
                          else  if(w.isVisited(cX-1, cY) && !w.hasPit(cX-1, cY) && (( w.getDirection() == World.DIR_DOWN) || (w.getDirection() == World.DIR_UP)) )
                            {
                                if( w.getDirection() == World.DIR_DOWN)
                                 {
                                    w.doAction(World.A_TURN_RIGHT);
                                     w.doAction(World.A_MOVE);
                                 }
                                 else if(w.getDirection() == World.DIR_UP)
                                    {
                                     w.doAction(World.A_TURN_LEFT);
                                     w.doAction(World.A_MOVE);
                                     }
                             }
                       
                          else if(w.isVisited(cX, cY-1) && !w.hasPit(cX, cY-1) && ((w.getDirection() == World.DIR_RIGHT) || (w.getDirection() == World.DIR_LEFT) ))
                            {
                                if(w.getDirection() == World.DIR_RIGHT)
                                {
                                     w.doAction(World.A_TURN_RIGHT);
                                    w.doAction(World.A_MOVE);
                                }
                                 if(w.getDirection() == World.DIR_LEFT)
                                 {
                                     w.doAction(World.A_TURN_LEFT);
                                     w.doAction(World.A_MOVE);
                                 }
                            }
                  //  }
                    else
                    {
                    System.out.println("45_53.3");
                        w.doAction(World.A_TURN_RIGHT);
                        w.doAction(World.A_TURN_RIGHT);
                        w.doAction(World.A_MOVE);
                    }
                }
                
            }  
        }// close BREEZE
        
//## NO STENCH AND BREEZE ##-------------------------------------------------------------------------------------
        
      if(!w.hasBreeze(cX, cY) && !w.hasStench(cX, cY) && move==0) // NO STENCH AND BREEZE
        {
          move=1;
          if(w.isUnknown(cX+1, cY) && w.isValidPosition(cX+1, cY))
            {
                System.out.println("54");
                if(w.getDirection() == World.DIR_DOWN)
                {
                    System.out.println("55");
                    w.doAction(World.A_TURN_LEFT);
                     w.doAction(World.A_MOVE);
                }
                else if(w.getDirection() == World.DIR_LEFT)
                {
                    System.out.println("56");
                    w.doAction(World.A_TURN_RIGHT);
                    w.doAction(World.A_TURN_RIGHT);
                     w.doAction(World.A_MOVE);
                }
                else if(w.getDirection() == World.DIR_UP)
                {
                    System.out.println("57");
                    w.doAction(World.A_TURN_RIGHT);
                     w.doAction(World.A_MOVE);
                }
                else
                {
                    System.out.println("54.1: ERROR");
                    w.doAction(World.A_MOVE);
                }
            }
          else if(w.isUnknown(cX, cY+1) && w.isValidPosition(cX, cY+1))
            {
                
                if(w.getDirection() == World.DIR_DOWN)
                {
                    System.out.println("47");
                w.doAction(World.A_TURN_RIGHT);
                w.doAction(World.A_TURN_RIGHT);
                 w.doAction(World.A_MOVE);
                }
                else if(w.getDirection() == World.DIR_LEFT)
                {
                    System.out.println("48");
                w.doAction(World.A_TURN_RIGHT);
                 w.doAction(World.A_MOVE);
                }
               else if(w.getDirection() == World.DIR_RIGHT)
                {
                    System.out.println("49");
                w.doAction(World.A_TURN_LEFT);
                 w.doAction(World.A_MOVE);
                }
               else
               {
                   System.out.println("46.1: ERROR");
                   w.doAction(World.A_MOVE);
               }
               // if(w.getDirection() == World.DIR_UP)
            }
            else if(w.isUnknown(cX, cY-1) && w.isValidPosition(cX, cY-1))
            {
                //if(w.getDirection() == World.DIR_DOWN)
                System.out.println("50");
                if(w.getDirection() == World.DIR_LEFT)
                {
                    System.out.println("51");
                    w.doAction(World.A_TURN_LEFT);
                     w.doAction(World.A_MOVE);
                }
               else if(w.getDirection() == World.DIR_RIGHT)
                {
                    System.out.println("52");
                    w.doAction(World.A_TURN_RIGHT);
                     w.doAction(World.A_MOVE);
                }
               else if(w.getDirection() == World.DIR_UP)
                {
                    System.out.println("53");
                    w.doAction(World.A_TURN_RIGHT);
                    w.doAction(World.A_TURN_RIGHT);
                     w.doAction(World.A_MOVE);
                }
                else
               {
                   System.out.println("50.1: ERROR");
                   w.doAction(World.A_MOVE);
               }
            }
          else if(w.isUnknown(cX-1, cY) && w.isValidPosition(cX-1, cY))
            {
                System.out.println("58: entered");
                if(w.getDirection() == World.DIR_DOWN)
                {
                    System.out.println("59");
                    w.doAction(World.A_TURN_RIGHT);
                     w.doAction(World.A_MOVE);
                }
               // if(w.getDirection() == World.DIR_LEFT)
                else if(w.getDirection() == World.DIR_RIGHT)
                {
                    System.out.println("60");
                w.doAction(World.A_TURN_LEFT);
                w.doAction(World.A_TURN_LEFT);
                 w.doAction(World.A_MOVE);
                }
                else if(w.getDirection() == World.DIR_UP)
                {
                    System.out.println("61");
                w.doAction(World.A_TURN_LEFT);
                w.doAction(World.A_MOVE);
                }
                else
                {
                    System.out.println("58.1: Error");
                    w.doAction(World.A_MOVE);
                }
            }
          else
          {
          System.out.println("JUST MOVE");
          w.doAction(World.A_MOVE);
          }
       }   
   // END OF NO STENCH AND BREEZE -------------XXXXX------------------XXXXX------------------XXXXX---------
    } //doaction
}// class

