/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package secondquestion;

/**
 *
 * @author sid
 */
public class studentParameters {
    private String rollNumber;
    private int attendence;
    private int totalClasses; 
    private int approvedLeaves;
    private int assignmentMarks[] = new int[5];
    private int midsemMarks[] = new int[2];
    private int endsemMarks[] = new int[2];
    private int midsemMark;
    void set_rollNumber(String rollNumber)
    {
        this.rollNumber = rollNumber;
    }
    
    void set_attendence(int attendence,int totalClasses)
    {
        this.attendence = attendence;
        this.totalClasses = totalClasses;
    }
    
    void set_approvedLeaves(int approvedLeaves)
    {
        this.approvedLeaves = approvedLeaves;
    }
    void set_assignmentMarks(int assignmentMarks[])
    {
        for(int i=0;i<5;i++)
        {
            this.assignmentMarks[i] = assignmentMarks[i];
        }
    }
    void set_midsemMarks(int midsemMarks[])
    {
        for(int i=0;i<2;i++)
        {
            this.midsemMarks[i] = midsemMarks[i];
        }
        midsemMark = Integer.max(midsemMarks[0], midsemMarks[1]);
    }
    void set_endsemMarks(int endsemMarks[])
    {
        for(int i=0;i<2;i++)
        {
            this.endsemMarks[i] = endsemMarks[i];
        }
    }
    String get_rollNumber()
    {
        return rollNumber;
    }
    int get_total()
    {
        int p = 0;
        for(int i=0;i<5;i++)
        {
            p+= assignmentMarks[i];
        }
        for(int i=0;i<2;i++)
        {
            p+=endsemMarks[i];
        }
        p+=midsemMark;
        return p;
    }
    boolean isSafe()
    {
        double p = (attendence*100)/totalClasses;
        double q = (approvedLeaves*100)/totalClasses;
        
        p += Double.min(q,35.00);
        return p>=75.00;
    }
}
