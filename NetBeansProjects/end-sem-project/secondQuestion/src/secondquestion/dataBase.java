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
public class dataBase {
    
    private studentParameters sps[] = new studentParameters[1000];
    private int counter;
    private double p1,p2,p3,p4,p5,p6;
    private String grade[] = new String[1000];
    private int marks[] = new int[1000];
    private int penalty[] = new int[1000];
    void set_ps(int p1,int p2,int p3,int p4,int p5,int p6)
    {
           this.p1 = p1;
           this.p2 = p2;
           this.p3 = p3;
           this.p4 = p4;
           this.p5 = p5;
           this.p6 = p6;
    }
    public dataBase() {
        counter = 0;
        for(int i=0;i<1000;i++)
        {
            penalty[i] = 0;
        }
    }
    
    void addPenalty(String s,int p)
    {
        for(int i=0;i<counter;i++)
        {
            if(sps[i].get_rollNumber().equals(s))
            {
                penalty[i] = p;
                break;
            }
        }
    }
    void addStudentParameter(studentParameters sp)
    {
        sps[counter++] = sp;
    }
    
    int MinimumMarks(String g)
    {
        MainFrame.db.computeGrades();
        int ret = 1000000;
        for(int i=0;i<counter;i++)
        {
            if(grade[i].equals(g))
            {
                ret = Math.min(marks[i],ret);
            }
        }
        
        return ret;
    }
    int MaximumMarks(String g)
    {
        MainFrame.db.computeGrades();
        int ret = -1;
        for(int i=0;i<counter;i++)
        {
            if(grade[i].equals(g))
            {
                ret = Math.max(marks[i],ret);
            }
        }
        
        return ret;
    }
    int count(String g)
    {
        MainFrame.db.computeGrades();
        int ret = 0;
        for(int i=0;i<counter;i++)
        {
            if(grade[i].equals(g)){
                ret++;
            }
        }
        return ret;
    }
    String get_grade(String rollNumber)
    {
        MainFrame.db.computeGrades();
        for(int i=0;i<counter;i++)
        {
            if(rollNumber.equals(sps[i].get_rollNumber()))
                return grade[i];
        }
        return "F";
    }
    int get_rank(String rollNumber)
    {
        MainFrame.db.computeGrades();
        for(int i=0;i<counter;i++)
        {
            if(rollNumber.equals(sps[i].get_rollNumber()))
            {
                return counter-i;
            }
        }
        return counter;
    }
    void computeGrades()
    {
        for(int i=0;i<counter;i++)
        {
            if(!sps[i].isSafe())
            {
                marks[i] = -1;
                continue;
            }
            marks[i] = sps[i].get_total() - penalty[i];
        }
        for(int i=0;i<counter;i++)
        {
            for(int j=i+1;j<counter;j++)
            {
                if(marks[j] < marks[i])
                {
                    int tmp = marks[i];
                    marks[i] = marks[j];
                    marks[j] = tmp;
                    studentParameters spp = sps[i];
                    sps[i] = sps[j];
                    sps[j] = spp;
                    
                    tmp = penalty[i];
                    penalty[i] = penalty[j];
                    penalty[j] = tmp;
                }
            }
        }
        
        int i = counter-1;
        int f1 =(int) (p1*counter)/100;
        int f2 =(int) (p2*counter)/100;
        int f3 =(int) (p3*counter)/100;
        int f4 =(int) (p4*counter)/100;
        int f5 =(int) (p5*counter)/100;
        int f6 =(int) (p6*counter)/100;
        
        if(p1 >0 && f1==0) f1++;
        
        while(i>=0 && f1>0)
        {
            if(marks[i]!=-1)
                grade[i] = "A+";
            else
                grade[i] = "F";
            i--;
        }
        while(i>=0 && f2>0)
        {
            if(marks[i]!=-1)
                grade[i] = "A";
            else
                grade[i] = "F";
            i--;
        }
        while(i>=0 && f3>0)
        {
            if(marks[i]!=-1)
                grade[i] = "B+";
            else
                grade[i] = "F";
            i--;
        }
        while(i>=0 && f4>0)
        {
            if(marks[i]!=-1)
                grade[i] = "B";
            else
                grade[i] = "F";
            i--;
        }
        while(i>=0 && f5>0)
        {
            if(marks[i]!=-1)
                grade[i] = "C";
            else
                grade[i] = "F";
            i--;
        }
        while(i>=0 && f6>0)
        {
            if(marks[i]!=-1)
                grade[i] = "D";
            else
                grade[i] = "F";
            i--;
        }
        while(i>=0)
        {
            grade[i] = "F";
            i--;
        }        
    }
}
