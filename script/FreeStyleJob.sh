cd $WORKSPACE
if ![[`git branch --list $BRANCH_NAME` ]]
then
	git branch test
fi
git switch test
git pull origin test
java -cp ".:./lib/*" org.junit.runner.JUnitCore model.TestStringCalculator
git pull origin main
git push origin main