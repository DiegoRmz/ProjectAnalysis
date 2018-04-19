/**
 * Project
 */
package model;


public class Project {
    public String projectOwner;
    public String projectName;
    public int    periods;

    public void setProjectOwner(String projectOwner) {
        this.projectOwner = projectOwner;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectOwner() {
        return this.projectOwner;
    }

    public String getProjectName() {
        return this.projectName;
    }
}