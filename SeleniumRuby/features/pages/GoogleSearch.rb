class GoogleSearch
  @element=nil
  
  def getURL
    $gDriver.get "http://google.com"
  end
  
  def enterSearch
    @element = $gDriver.find_element :name => "q"
    @element.send_keys "Cheese!"
  end
  
  def clickSubmit
    @element.submit
  end
end