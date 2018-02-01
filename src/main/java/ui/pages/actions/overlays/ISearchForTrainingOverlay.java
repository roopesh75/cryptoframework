package ui.pages.actions.overlays;

import ui.pages.actions.TrainingManagementPage;

public interface ISearchForTrainingOverlay {
	
	public String getSearchStatus();
	public String getResultsFromSearchOverlay();
	public TrainingManagementPage closeSearchTrainingOverlay();
	public TrainingManagementPage searchTraining(String trainingCode);
	public TrainingManagementPage chooseVersion(String trainingCode,String version);
}
