## 8 CI/CD best practices
For all the following parts of this chapter, a better possibility is to encapsulate your command in some shell scripts.  
You can also use a Makefile.

Both have been done here:
- the scripts are in the **pipeline/scripts** folder
- a **makefile** can be found at the root of this project. It uses the scripts of the previous named folder.

Build or deployment are in the scripts.
Makefile or gitlab-ci.yaml are the pipelines.

Next to come:
- Artifact/image promotion (snapshot to release): build once, run everywhere 
- Continuous deployment