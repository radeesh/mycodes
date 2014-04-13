require_relative '../pages/GoogleSearch'

@pObj=nil
Given(/^I have opened Google.com$/)do
  @pObj = GoogleSearch.new
  @pObj.getURL
end
When /^I search for "Wikipedia"$/ do
  @pObj.enterSearch  
end
Then /^search results should be displayed$/ do
  @pObj.clickSubmit
end