EasyJ - The jQuery for Java
===========================

EasyJ is a Java library which simplifies Java development by providing jQuery like interface to work with Java Collections(List & Map) and Files. 
It eases the development by exposing methods to perform day-to-day Java tasks. EasyJ is built upon the OGNL library for Java which is a part 
of the OpenSymnphony project.

Sample Code
===========
Here is how a sample code of EasyJ looks like...

Suppose list is an ArrayList containing Employee objects. To filter out all employees having salary greater than 2000 and job type as permanent 
and then generate a list of names of such employees, one would write the following code.

J.$(list).find("{ salary > 2000 & jobType == 'P' }").project("{name}").list();