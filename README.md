EasyJ - The jQuery for Java
===========================

EasyJ is a Java library which simplifies Java development by providing jQuery like interface to work with Java Collections(List & Map) and Files. 
It eases the development by exposing methods which perform day-to-day Java tasks. EasyJ is built upon the OGNL library for Java which is a part 
of the OpenSymnphony project.

Sample Code
===========
Here is how a sample code of EasyJ looks like...

Suppose list is an ArrayList containing Employee objects. To filter out all employees having salary greater than 2000 and job type as permanent and then generate a list of names of those employees, one would write the following code.


`// e.g., Employee1 = { name = "John Doe", salary=3000, jobType='P'} ... and so on.` 
`J.$(list).find("{ salary > 2000 && jobType == 'P' }").project("{name}").list();`

Links
-----

  * [Home Page](http://easy-j.com/)
  * [Documentation](http://easy-j.com/api.html)
  * [Latest Production Release](http://easy-j.com/easyj.jar)
  * [Forums](http://theeasyj.blogspot.com/)
  * [Contributor Info](http://easy-j.com#whoami)
  * [Report a Bug](mailto:khanm.developer@gmail.com)

Download and Build
------------------

Clone the project 

`$ git clone git://github.com/khanmdv/easyj.git`

EasyJ uses Ant to build the project. Download ant from [here](http://ant.apache.org/bindownload.cgi)

In the base directory, run `ant`. The `dist` directory should get created. The easyj jar file is 
available in the `dist` directory.

Source Info
-----------

The EasyJ source tree includes the following directories/files:

  * `ref`: This folder contains the dependent OGNL libraries from Open Symphony.

  * `src`: Raw unbuilt source code for the library.

  * `build.properties`: Contains properties required for Ant build.
  
  * `build.xml`: Ant build file. 


