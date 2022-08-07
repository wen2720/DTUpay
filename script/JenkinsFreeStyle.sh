### Important, the shell script is just for example. Freestyle job is created to for testing pipeline script .
### E.g. If branch doesn't exist then create a branch test else just switch to test branch
### point latest remote test branch
### run Junit test 
### point to latest remote main 
### merge local test to main
### point to latest remote main 
### push to main branch
cd $WORKSPACE
if ![[`git branch --list $BRANCH_NAME` ]]
then
	git branch test
fi
git switch test
git pull origin test
java -cp ".:./lib/*" org.junit.runner.JUnitCore model.TestStringCalculator
if ![[`git branch --list $BRANCH_NAME` ]]
then
	git branch main
fi
git switch main
git switch main
git pull origin main
git merge test