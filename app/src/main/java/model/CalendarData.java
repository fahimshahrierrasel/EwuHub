package model;

/**
 * Created by fahimshahrier on 2/26/16.
 */
public class CalendarData
{
    String undergradute;
    String undergradutePharmacy;
    String graduate;
    String graduatePharmacy;

    public CalendarData()
    {
        undergradute = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "\t<style>\n" +
                "\t\ttable {\n" +
                "\t\twidth:100%;\n" +
                "\t\t}\n" +
                "\t\ttable, th, td {\n" +
                "\t\tborder: 1px solid black;\n" +
                "\t\tborder-collapse: collapse;\n" +
                "\t\t}\n" +
                "\t\tth, td {\n" +
                "\t\tpadding: 5px;\n" +
                "\t\ttext-align: center;\n" +
                "\t\t}\n" +
                "\t\th2 {\n" +
                "\t\ttext-align: center;\n" +
                "\t\t}\n" +
                "\t</style>\n" +
                "</head>\n" +
                "<body>\n" +
                "<h2>Academic Calendar for Undergraduate Spring-2016</h2>\n" +
                "<table>\n" +
                "\t<tr>\n" +
                "\t\t<th>Date</th>\n" +
                "\t\t<th>Day</th>\n" +
                "\t\t<th>Events</th>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<td>January 05</td>\n" +
                "\t\t<td>Tuesday</td>\n" +
                "\t\t<td>University Reopens</td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<td>January 07</td>\n" +
                "\t\t<td>Thursday</td>\n" +
                "\t\t<td>Departmental Orientation and Advising of Newly Admitted Students and Knowing your Campus: Orientation with the academic and other facilities of the University</td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<td>January 10</td>\n" +
                "\t\t<td>Sunday</td>\n" +
                "\t\t<td>\n" +
                "\t\t\t<li>First Day of Classes</li>\t\n" +
                "\t\t\t<li>Payment of tuition fees for continuing students: As per the Payment Schedule provided in the table below (12 January 2016 to 21 January 2016) </li>\n" +
                "\t\t</td>\n" +
                "\t</tr>\n" +
                "\t\n" +
                " \t<tr>\n" +
                "\t\t<td>January 12</td>\n" +
                "\t\t<td>Tuesday</td>\n" +
                "\t\t<td>\n" +
                "\t\t\t<li>Last day to Add Courses</li>\n" +
                "\t\t\t<li>Last day to Drop Course(s)/Semester with 100% Refund </li>\n" +
                "\t\t</td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<td>January 18</td>\n" +
                "\t\t<td>Monday</td>\n" +
                "\t\t<td>Last day to clear Incomplete grades ( \"I\" grade ) </td>\n" +
                "\t</tr>\t\n" +
                "\n" +
                "\t<tr>\n" +
                "\t\t<td>January 21</td>\n" +
                "\t\t<td>Thursday</td>\n" +
                "\t\t<td>\n" +
                "\t\t\t<li>Last date of payment of tuition fees without late fee</li>\n" +
                "\t\t\t<li>Last day of payment only for adding course(s) </li>\n" +
                "\t\t</td>\n" +
                "\t</tr>\n" +
                "\n" +
                "\t<tr>\n" +
                "\t\t<td>January 26</td>\n" +
                "\t\t<td>Tuesday</td>\n" +
                "\t\t<td>Classes suspended on account of Convocation </td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<td>January 27</td>\n" +
                "\t\t<td>Wednesday</td>\n" +
                "\t\t<td>15th Convocation (2016) </td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<td>January 28</td>\n" +
                "\t\t<td>Thursday</td>\n" +
                "\t\t<td>Holiday on account of Convocation </td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<td>January 31</td>\n" +
                "\t\t<td>Sunday</td>\n" +
                "\t\t<td>Last day of Tuition Payment with Late Fee of Tk. 500/- </td>\n" +
                "\t</tr>\n" +
                "  \t<tr>\n" +
                "\t\t<td>February 03</td>\n" +
                "\t\t<td>Wednesday</td>\n" +
                "\t\t<td>Last day of Tuition Payment with Late Fee of Tk. 1000/- </td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<td>February 07</td>\n" +
                "\t\t<td>Sunday</td>\n" +
                "\t\t<td>Last day to Drop Course(s)/Semester with 85% Refund </td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<td>February 08</td>\n" +
                "\t\t<td>Monday</td>\n" +
                "\t\t<td>Blocking of ID Numbers of Defaulting Studentss </td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<td>February 13</td>\n" +
                "\t\t<td>Saturday</td>\n" +
                "\t\t<td>Holiday: Saraswati Puja </td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<td>February 14-18</td>\n" +
                "\t\t<td>Sunday-Thursday</td>\n" +
                "\t\t<td>Mid Term I Examinations </td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<td>February 21</td>\n" +
                "\t\t<td>Sunday</td>\n" +
                "\t\t<td>Holiday: Shaheed Day &amp; International Mother Language Day </td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<td>February 28</td>\n" +
                "\t\t<td>Sunday</td>\n" +
                "\t\t<td>Last Day to Drop Course(s)/Semester with 50% Refund </td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<td>March 13-20</td>\n" +
                "\t\t<td>Sunday-Sunday</td>\n" +
                "\t\t<td>Mid Term II Examinations </td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<td>March 17</td>\n" +
                "\t\t<td>Thursday</td>\n" +
                "\t\t<td>Holiday: Birthday of the Father of the Nation Bangabandhu Sheikh Mujibur Rahman </td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<td>March 26</td>\n" +
                "\t\t<td>Saturday</td>\n" +
                "\t\t<td>Holiday: Independence &amp; National Day </td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<td>March 28</td>\n" +
                "\t\t<td>Monday</td>\n" +
                "\t\t<td>Regular Thursday Classes </td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<td>March 30</td>\n" +
                "\t\t<td>Wednesday</td>\n" +
                "\t\t<td>Last day of Withdrawal of Course(s)/Semester (\"W\" grade) </td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<td>April 02</td>\n" +
                "\t\t<td>Saturday</td>\n" +
                "\t\t<td>Advising of Courses for Summer 2016 (ongoing students) </td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<td>April 07</td>\n" +
                "\t\t<td>Thursday</td>\n" +
                "\t\t<td>Last Day of Classes </td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<td>April 09</td>\n" +
                "\t\t<td>Saturday</td>\n" +
                "\t\t<td>Admission Test for Summer 2016 </td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<td>April 10-17</td>\n" +
                "\t\t<td>Sunday-Sunday</td>\n" +
                "\t\t<td>Final Examinations </td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<td>April 14</td>\n" +
                "\t\t<td>Thursday</td>\n" +
                "\t\t<td>Holiday: Bengali New Year&#39;s Day </td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<td>April 20</td>\n" +
                "\t\t<td>Wednesday</td>\n" +
                "\t\t<td>Submission of Final Grades </td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<td>April 22-May 02</td>\n" +
                "\t\t<td>Friday-Monday</td>\n" +
                "\t\t<td>Semester Break </td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<td>May 01</td>\n" +
                "\t\t<td>Sunday</td>\n" +
                "\t\t<td>Holiday: May Day </td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<td>May 03</td>\n" +
                "\t\t<td>Tuesday</td>\n" +
                "\t\t<td>University Reopens for Summer 2016 </td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<td>May 05</td>\n" +
                "\t\t<td>Thursday</td>\n" +
                "\t\t<td>Orientation for Summer 2016 </td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<td>May 08</td>\n" +
                "\t\t<td>Sunday</td>\n" +
                "\t\t<td>First Day of Classes for Summer 2016 </td>\n" +
                "\t</tr>\n" +
                "\n" +
                "</table>\n" +
                "</body>\n" +
                "</html>";
        undergradutePharmacy = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "\t<style>\n" +
                "\t\ttable {\n" +
                "\t\twidth:100%;\n" +
                "\t\t}\n" +
                "\t\ttable, th, td {\n" +
                "\t\tborder: 1px solid black;\n" +
                "\t\tborder-collapse: collapse;\n" +
                "\t\t}\n" +
                "\t\tth, td {\n" +
                "\t\tpadding: 5px;\n" +
                "\t\ttext-align: center;\n" +
                "\t\t}\n" +
                "\t\th2 {\n" +
                "\t\ttext-align: center;\n" +
                "\t\t}\n" +
                "\t</style>\n" +
                "</head>\n" +
                "<body>\n" +
                "<h2>Academic Calendar for Undergraduate Pharmacy Spring-2016</h2>\n" +
                "<table>\n" +
                "\t<tr>\n" +
                "\t\t<th>Date</th>\n" +
                "\t\t<th>Day</th>\n" +
                "\t\t<th>Events</th>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<td>January 05</td>\n" +
                "\t\t<td>Tuesday</td>\n" +
                "\t\t<td>University Reopens</td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<td>January 07</td>\n" +
                "\t\t<td>Thursday</td>\n" +
                "\t\t<td>Departmental Orientation and Advising of Newly Admitted Students and Knowing your Campus: Orientation with the academic and other facilities of the University</td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<td>January 10</td>\n" +
                "\t\t<td>Sunday</td>\n" +
                "\t\t<td><li>First Day of Classes</li>\n" +
                "\t\n" +
                "<li>Payment of tuition fees for continuing students: As per the Payment Schedule provided in the table below (12 January 2016 to 21 January 2016) </li></td>\n" +
                "\t</tr>\n" +
                "\t\n" +
                " <tr>\n" +
                "\t\t<td>January 12</td>\n" +
                "\t\t<td>Tuesday</td>\n" +
                "\t\t<td><li>Last day to Add Courses</li>\n" +
                "\n" +
                "<li>Last day to Drop Course(s)/Semester with 100% Refund </li> </td>\n" +
                "<tr>\n" +
                "\t\t<td>January 18</td>\n" +
                "\t\t<td>Monday</td>\n" +
                "\t\t<td>Last day to clear Incomplete grades ( \"I\" grade ) </td>\n" +
                "\t</tr>\t\n" +
                "\n" +
                "<tr>\n" +
                "\t\t<td>January 21</td>\n" +
                "\t\t<td>Thursday</td>\n" +
                "\t\t<td><li>Last date of payment of tuition fees without late fee</li>\n" +
                "\n" +
                "<li>Last day of payment only for adding course(s) </li> </td>\n" +
                "\n" +
                "<tr>\n" +
                "\t\t<td>January 26</td>\n" +
                "\t\t<td>Tuesday</td>\n" +
                "\t\t<td>Classes suspended on account of Convocation </td>\n" +
                "\t</tr>\n" +
                "<tr>\n" +
                "\t\t<td>January 27</td>\n" +
                "\t\t<td>Wednesday</td>\n" +
                "\t\t<td>15th Convocation (2016) </td>\n" +
                "\t</tr>\n" +
                "<tr>\n" +
                "\t\t<td>January 28</td>\n" +
                "\t\t<td>Thursday</td>\n" +
                "\t\t<td>Holiday on account of Convocation </td>\n" +
                "\t</tr>\n" +
                "<tr>\n" +
                "\t\t<td>January 31</td>\n" +
                "\t\t<td>Sunday</td>\n" +
                "\t\t<td>Last day of Tuition Payment with Late Fee of Tk. 500/- </td>\n" +
                "\t</tr>\n" +
                "  <tr>\n" +
                "\t\t<td>February 03</td>\n" +
                "\t\t<td>Wednesday</td>\n" +
                "\t\t<td>Last day of Tuition Payment with Late Fee of Tk. 1000/- </td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<td>February 07</td>\n" +
                "\t\t<td>Sunday</td>\n" +
                "\t\t<td>Last day to Drop Course(s)/Semester with 85% Refund </td>\n" +
                "\t</tr>\n" +
                "<tr>\n" +
                "\t\t<td>February 08</td>\n" +
                "\t\t<td>Monday</td>\n" +
                "\t\t<td>Blocking of ID Numbers of Defaulting Studentss </td>\n" +
                "\t</tr>\n" +
                "<tr>\n" +
                "\t\t<td>February 13</td>\n" +
                "\t\t<td>Saturday</td>\n" +
                "\t\t<td>Holiday: Saraswati Puja </td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<td>February 21</td>\n" +
                "\t\t<td>Sunday</td>\n" +
                "\t\t<td>Holiday: Shaheed Day &amps International Mother Language Day </td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<td>March 04-12</td>\n" +
                "\t\t<td>Friday-Saturday</td>\n" +
                "\t\t<td>Mid Term I Examinations </td>\n" +
                "\t</tr>\n" +
                "\n" +
                "\t<tr>\n" +
                "\t\t<td>March 17</td>\n" +
                "\t\t<td>Thursday</td>\n" +
                "\t\t<td>Holiday: Birthday of the Father of the Nation Bangabandhu Sheikh Mujibur Rahman </td>\n" +
                "\t</tr>\n" +
                "      <tr>\n" +
                "\t\t<td>March 20</td>\n" +
                "\t\t<td>Sunday</td>\n" +
                "\t\t<td>Last Day to Drop Course(s)/Semester with 50% Refund </td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<td>March 26</td>\n" +
                "\t\t<td>Saturday</td>\n" +
                "\t\t<td>Holiday: Independence &amp; National Day </td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<td>April 09</td>\n" +
                "\t\t<td>Saturday</td>\n" +
                "\t\t<td>Admission Test for Summer 2016 </td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<td>April 14</td>\n" +
                "\t\t<td>Thursday</td>\n" +
                "\t\t<td>Holiday: Bengali New Year&#39;s Day </td>\n" +
                "\t</tr>\n" +
                "\t\t<tr>\n" +
                "\t\t<td>April 15-23</td>\n" +
                "\t\t<td>Friday-Saturday</td>\n" +
                "\t\t<td>Mid Term II Examinations </td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<td>May 01</td>\n" +
                "\t\t<td>Sunday</td>\n" +
                "\t\t<td>Holiday: May Day </td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<td>May 03</td>\n" +
                "\t\t<td>Tuesday</td>\n" +
                "\t\t<td>University Reopens for Summer 2016 </td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<td>May 05</td>\n" +
                "\t\t<td>Thursday</td>\n" +
                "\t\t<td>Last day of Withdrawal of Course(s)/Semester (\"W\" grade) </td>\n" +
                "\t</tr>\n" +
                "\n" +
                "\t<tr>\n" +
                "\t\t<td>May 21</td>\n" +
                "\t\t<td>Saturday</td>\n" +
                "\t\t<td>Holiday: Buddha Purnima * </td>\n" +
                "\t</tr>\n" +
                "\n" +
                "<tr>\n" +
                "\t\t<td>May 23</td>\n" +
                "\t\t<td>Monday</td>\n" +
                "\t\t<td>Holiday: Shab-E-Barat *</td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<td>May 24</td>\n" +
                "\t\t<td>Tuesday</td>\n" +
                "\t\t<td>Regular Thursday Classes </td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<td>May 28</td>\n" +
                "\t\t<td>Saturday</td>\n" +
                "\t\t<td>Advising of Courses for Fall 2016 (ongoing students) </td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<td>June 01</td>\n" +
                "\t\t<td>Wednesday</td>\n" +
                "\t\t<td>Regular Thursday Classes </td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<td>June 02</td>\n" +
                "\t\t<td>Thursday</td>\n" +
                "\t\t<td>Last Day of Classes </td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<td>June 05-13</td>\n" +
                "\t\t<td>Sunday-Monday</td>\n" +
                "\t\t<td>Final Examinations </td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<td>June 15</td>\n" +
                "\t\t<td>Wednesday</td>\n" +
                "\t\t<td>Submission of Final Grades </td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<td>June 17-25</td>\n" +
                "\t\t<td>Friday-Monday</td>\n" +
                "\t\t<td>Semester Break </td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<td>June 26</td>\n" +
                "\t\t<td>Sunday</td>\n" +
                "\t\t<td>First Day of Classes for Fall 2016 </td>\n" +
                "\t</tr>\n" +
                "\n" +
                "\n" +
                "</table>\n" +
                "</body>\n" +
                "</html>";
        graduate = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "\t<style>\n" +
                "\t\ttable {\n" +
                "\t\twidth:100%;\n" +
                "\t\t}\n" +
                "\t\ttable, th, td {\n" +
                "\t\tborder: 1px solid black;\n" +
                "\t\tborder-collapse: collapse;\n" +
                "\t\t}\n" +
                "\t\tth, td {\n" +
                "\t\tpadding: 5px;\n" +
                "\t\ttext-align: center;\n" +
                "\t\t}\n" +
                "\t\th2 {\n" +
                "\t\ttext-align: center;\n" +
                "\t\t}\n" +
                "\t</style>\n" +
                "</head>\n" +
                "<body>\n" +
                "<h2>Academic Calendar for Graduate Summer-2016</h2>\n" +
                "<table>\n" +
                "\t<tr>\n" +
                "\t\t<th>Date</th>\n" +
                "\t\t<th>Day</th>\n" +
                "\t\t<th>Events</th>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<td>January 05</td>\n" +
                "\t\t<td>Tuesday</td>\n" +
                "\t\t<td>University Reopens</td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<td>January 07</td>\n" +
                "\t\t<td>Thursday</td>\n" +
                "\t\t<td><li>Orientation Program for Newly Admitted Students (Start from 10:00 a.m.)\n" +
                "(Participation is Mandatory for all new students)</li>\n" +
                "<li>Advising for Newly Admitted Students (Spring 2016) from 03:00 pm in Program Office</li></td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<td>January 08</td>\n" +
                "\t\t<td>Friday</td>\n" +
                "\t\t<td><li>  First Day of Classes</li>\n" +
                "       <li>Payment of tuition fees for continuing students:\n" +
                "       From 12 January 2016 to 21 January 2016 by 10:00 a.m. to 4:00 p.m.</li></td>\n" +
                "\t</tr>\n" +
                "\t\n" +
                " <tr>\n" +
                "\t\t<td>January 13</td>\n" +
                "\t\t<td>Tuesday</td>\n" +
                "\t\t<td><li>Last date for Adding Course(s)</li>\n" +
                "<li>Last day to Drop Course(s)/Semester from 02.00 pm-06.00 pm with 100% Refund </li></td>\n" +
                "<tr>\n" +
                "\t\t<td>January 18</td>\n" +
                "\t\t<td>Monday</td>\n" +
                "\t\t<td>Last day to clear Incomplete grades ( \"I\" grade ) </td>\n" +
                "\t</tr>\t\n" +
                "\n" +
                "<tr>\n" +
                "\t\t<td>January 21</td>\n" +
                "\t\t<td>Thursday</td>\n" +
                "\t\t<td><li>Last date of payment of tuition fees without late fee (Except Friday and Saturday) during : 10:00 a.m. to 04:00 p.m.</li>\n" +
                "\n" +
                "<li>Last day of payment only for adding course(s) </li> </td>\n" +
                "\n" +
                "<tr>\n" +
                "\t\t<td>January 26</td>\n" +
                "\t\t<td>Tuesday</td>\n" +
                "\t\t<td>Classes suspended on account of Convocation </td>\n" +
                "\t</tr>\n" +
                "<tr>\n" +
                "\t\t<td>January 27</td>\n" +
                "\t\t<td>Wednesday</td>\n" +
                "\t\t<td>15th Convocation (2016) </td>\n" +
                "\t</tr>\n" +
                "<tr>\n" +
                "\t\t<td>January 28</td>\n" +
                "\t\t<td>Thursday</td>\n" +
                "\t\t<td>Holiday on account of Convocation </td>\n" +
                "\t</tr>\n" +
                "<tr>\n" +
                "\t\t<td>January 31</td>\n" +
                "\t\t<td>Sunday</td>\n" +
                "\t\t<td>Last day of Tuition Payment with Late Fee of Tk. 500/- </td>\n" +
                "\t</tr>\n" +
                "  <tr>\n" +
                "\t\t<td>February 03</td>\n" +
                "\t\t<td>Wednesday</td>\n" +
                "\t\t<td>Last day of Tuition Payment with Late Fee of Tk. 1000/- </td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<td>February 07</td>\n" +
                "\t\t<td>Sunday</td>\n" +
                "\t\t<td>Last day to Drop Course(s)/Semester with 85% Refund </td>\n" +
                "\t</tr>\n" +
                "<tr>\n" +
                "\t\t<td>February 08</td>\n" +
                "\t\t<td>Monday</td>\n" +
                "\t\t<td>Blocking of ID Numbers of Defaulting Studentss </td>\n" +
                "\t</tr>\n" +
                "<tr>\n" +
                "<tr>\n" +
                "\t\t<td>February 12-16</td>\n" +
                "\t\t<td>Friday-Tuesday</td>\n" +
                "\t\t<td>Mid Term I Examinations </td>\n" +
                "\t</tr>\n" +
                "\t\t<td>February 13</td>\n" +
                "\t\t<td>Saturday</td>\n" +
                "\t\t<td>Holiday: Saraswati Puja </td>\n" +
                "\t</tr>\n" +
                "\t\n" +
                "\t<tr>\n" +
                "\t\t<td>February 21</td>\n" +
                "\t\t<td>Sunday</td>\n" +
                "\t\t<td>Holiday: Shaheed Day &amp; International Mother Language Day </td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<td>February 25</td>\n" +
                "\t\t<td>Thursday</td>\n" +
                "\t\t<td>Regular Sunday Classes</td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<td>February 28</td>\n" +
                "\t\t<td>Sunday</td>\n" +
                "\t\t<td>Last Day to Drop Course(s)/Semester with 50% Refund </td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<td>March 11-15</td>\n" +
                "\t\t<td>Friday-Tuesday</td>\n" +
                "\t\t<td>Mid Term II Examinations </td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<td>March 17</td>\n" +
                "\t\t<td>Thursday</td>\n" +
                "\t\t<td>Holiday: Birthday of the Father of the Nation Sheikh Mujibur Rahman </td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<td>March 26</td>\n" +
                "\t\t<td>Saturday</td>\n" +
                "\t\t<td>Holiday: Independence &amp; National Day </td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<td>March 30</td>\n" +
                "\t\t<td>Wednesday</td>\n" +
                "\t\t<td>Last day of Withdrawal of Course(s)/Semester (\"W\" grade) </td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<td>April 02</td>\n" +
                "\t\t<td>Saturday</td>\n" +
                "\t\t<td>Advising of Courses for Summer 2016 (ongoing students) </td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<td>April 09</td>\n" +
                "\t\t<td>Saturday</td>\n" +
                "\t\t<td>Admission Test of  MBA Program for  Summer 2016 </td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<td>April 10</td>\n" +
                "\t\t<td>Sunday</td>\n" +
                "\t\t<td>Last Day of Classes </td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<td>April 12-18</td>\n" +
                "\t\t<td>Tuesday-Monday</td>\n" +
                "\t\t<td>Final Examinations </td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<td>April 14</td>\n" +
                "\t\t<td>Thursday</td>\n" +
                "\t\t<td>Holiday: Bengali New Year&#39;s Day </td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<td>April 20</td>\n" +
                "\t\t<td>Wednesday</td>\n" +
                "\t\t<td>Submission of Final Grades </td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<td>April 22-May 02</td>\n" +
                "\t\t<td>Friday-Monday</td>\n" +
                "\t\t<td>Semester Break </td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<td>May 01</td>\n" +
                "\t\t<td>Sunday</td>\n" +
                "\t\t<td>Holiday: May Day </td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<td>May 03</td>\n" +
                "\t\t<td>Tuesday</td>\n" +
                "\t\t<td>University Reopens for Summer 2016 </td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<td>May 05</td>\n" +
                "\t\t<td>Thursday</td>\n" +
                "\t\t<td>Orientation for Summer 2016 </td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<td>May 08</td>\n" +
                "\t\t<td>Sunday</td>\n" +
                "\t\t<td>First Day of Classes for Summer 2016 </td>\n" +
                "\t</tr>\n" +
                "\n" +
                "</table>\n" +
                "</body>\n" +
                "</html>";
        graduatePharmacy = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "\t<style>\n" +
                "\t\ttable {\n" +
                "\t\twidth:100%;\n" +
                "\t\t}\n" +
                "\t\ttable, th, td {\n" +
                "\t\tborder: 1px solid black;\n" +
                "\t\tborder-collapse: collapse;\n" +
                "\t\t}\n" +
                "\t\tth, td {\n" +
                "\t\tpadding: 5px;\n" +
                "\t\ttext-align: center;\n" +
                "\t\t}\n" +
                "\t\th2 {\n" +
                "\t\ttext-align: center;\n" +
                "\t\t}\n" +
                "\t</style>\n" +
                "</head>\n" +
                "<body>\n" +
                "<h2>Academic Calendar for Graduate Pharmacy Summer-2016</h2>\n" +
                "<table>\n" +
                "\t<tbody><tr>\n" +
                "\t\t<th>Date</th>\n" +
                "\t\t<th>Day</th>\n" +
                "\t\t<th>Events</th>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<td>June 28</td>\n" +
                "\t\t<td>Sunday</td>\n" +
                "\t\t<td>Orientation &amp; First Day of Classes</td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<td>July 02 - 08 </td>\n" +
                "\t\t<td>Thursday-Wednesday</td>\n" +
                "\t\t<td>Payment of Tuition fee without penalty.</td>\n" +
                "\t</tr>\n" +
                "\t\n" +
                " <tr>\n" +
                "\t\t<td>July 05</td>\n" +
                "\t\t<td>Sunday</td>\n" +
                "\t\t<td>Last day to clear Incomplete grades ( \"I\" grade )</td>\n" +
                "</tr><tr>\n" +
                "\t\t<td>July 08</td>\n" +
                "\t\t<td>Wednesday</td>\n" +
                "\t\t<td>Last day to Add/Drop Course(s)/Semester with 100% Refund.</td>\n" +
                "\t</tr>\t\n" +
                "\n" +
                "<tr>\n" +
                "\t\t<td>July 13</td>\n" +
                "\t\t<td>Monday</td>\n" +
                "\t\t<td>Last day of payment for adding course(s)</td>\n" +
                "\n" +
                "</tr><tr>\n" +
                "\t\t<td>July 15-25</td>\n" +
                "\t\t<td>Wednesday-Saturday</td>\n" +
                "\t\t<td>Holiday: Shab-E-Qadr, Jumat-Ul-Wida &amp; Eid-Ul-Fitr * </td>\n" +
                "\t</tr>\n" +
                "<tr>\n" +
                "\t\t<td>July 26</td>\n" +
                "\t\t<td>Sunday</td>\n" +
                "\t\t<td>Regular Classes Resume </td>\n" +
                "\t</tr>\n" +
                "<tr>\n" +
                "\t\t<td>July 28</td>\n" +
                "\t\t<td>Tuesday</td>\n" +
                "\t\t<td>Last day to Drop Course(s)/Semester with 85% Refund</td>\n" +
                "\t</tr>\n" +
                "<tr>\n" +
                "\t\t<td>August 02</td>\n" +
                "\t\t<td>Sunday</td>\n" +
                "\t\t<td>Last day of Tuition Payment with Late Fee of Tk. 500/- </td>\n" +
                "\t</tr>\n" +
                "  <tr>\n" +
                "\t\t<td>August 09</td>\n" +
                "\t\t<td>Sunday</td>\n" +
                "\t\t<td>Last day of Tuition Payment with Late Fee of Tk. 1000/- </td>\n" +
                "\t</tr>\n" +
                "<tr>\n" +
                "\t\t<td>August 11</td>\n" +
                "\t\t<td>Tuesday</td>\n" +
                "\t\t<td>Blocking of ID Numbers of Defaulting Studentss </td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<td>August 15</td>\n" +
                "\t\t<td>Saturday</td>\n" +
                "\t\t<td>Holiday: National Mourning Day</td>\n" +
                "\t</tr>\n" +
                "\t\t<tr>\n" +
                "\t\t<td>September 05</td>\n" +
                "\t\t<td>Saturday</td>\n" +
                "\t\t<td>Holiday: Janmastami</td>\n" +
                "\t</tr>\n" +
                "\n" +
                "    <tr>\n" +
                "\t\t<td>September 12-19</td>\n" +
                "\t\t<td>Saturday-Saturday</td>\n" +
                "\t\t<td>Mid Term  Examinations </td></tr>\n" +
                "\t\n" +
                "\t<tr>\n" +
                "\t\t<td>Sep. 22-Oct. 03</td>\n" +
                "\t\t<td>Tuesday-Saturday</td>\n" +
                "\t\t<td>Holiday: Eid-Ul-Azha *</td>\n" +
                "\t</tr>\n" +
                "<tr>\n" +
                "\t\t<td>October 04</td>\n" +
                "\t\t<td>Sunday</td>\n" +
                "\t\t<td>Regular Classes Resume</td>\n" +
                "\t</tr>\n" +
                "\n" +
                "   <tr>\n" +
                "\t\t<td>October 05</td>\n" +
                "\t\t<td>Monday</td>\n" +
                "\t\t<td>Last Day to Drop Course(s)/Semester with 50% Refund </td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<td>October 22-23</td>\n" +
                "\t\t<td>Thursday-Friday</td>\n" +
                "\t\t<td>Holiday: Durgapuja (Bijoya Nabami &amp; Dashami)</td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<td>October 24</td>\n" +
                "\t\t<td>Saturday</td>\n" +
                "\t\t<td>Holiday: Muharram (Ashura) *</td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<td>November 18</td>\n" +
                "\t\t<td>Wednesday</td>\n" +
                "\t\t<td>Last day of Withdrawal of Course(s)/Semester (\"W\" grade) </td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<td>November 28</td>\n" +
                "\t\t<td>Saturday</td>\n" +
                "\t\t<td>Advising of Courses for Spring 2016 (ongoing students)</td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<td>December 03--13\n" +
                "</td>\n" +
                "\t\t<td>Thursday-Sunday</td>\n" +
                "\t\t<td>Final Examinations </td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<td>December 11</td>\n" +
                "\t\t<td>Friday</td>\n" +
                "\t\t<td>Admission Test for Spring 2016</td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<td>December 15</td>\n" +
                "\t\t<td>Tuesday</td>\n" +
                "\t\t<td>Submission of Final Grades </td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<td>December 16</td>\n" +
                "\t\t<td>Wednesday</td>\n" +
                "\t\t<td>Holiday: Victory Day</td>\n" +
                "\t</tr>\n" +
                "\n" +
                "\t<tr>\n" +
                "\t\t<td>Dec. 18-Jan. 04</td>\n" +
                "\t\t<td>Friday-Monday</td>\n" +
                "\t\t<td>Semester Break </td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<td>December 24</td>\n" +
                "\t\t<td>Thursday</td>\n" +
                "\t\t<td>Holiday: Eid-E-Miladdun-Nabi *</td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<td>December 25</td>\n" +
                "\t\t<td>Friday</td>\n" +
                "\t\t<td>Holiday: Christmas Day</td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<td>January 05</td>\n" +
                "\t\t<td>Tuesday</td>\n" +
                "\t\t<td>University Reopens for Spring 2016 </td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<td>January 07</td>\n" +
                "\t\t<td>Thursday</td>\n" +
                "\t\t<td>Orientation for Spring 2016 </td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<td>January 10</td>\n" +
                "\t\t<td>Sunday</td>\n" +
                "\t\t<td>First Day of Classes for Spring 2016 </td>\n" +
                "\t</tr>\n" +
                "</tbody></table></body></html>";
    }

    public String getUndergradute() {
        return undergradute;
    }

    public String getUndergradutePharmacy() {
        return undergradutePharmacy;
    }

    public String getGraduate() {
        return graduate;
    }

    public String getGraduatePharmacy() {
        return graduatePharmacy;
    }
}

