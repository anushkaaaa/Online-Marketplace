JCC = javac

default: AbstractFactory.class AdminFactory.class CustomerFactory.class Invoker.class Marketplace.class MarketplaceDispatcher.class MarketplaceFrontController.class MarketplaceModel.class MarketplaceServer.class MarketplaceView.class UserPage.class

AbstractFactory.class: AbstractFactory.java
	$(JCC) AbstractFactory.java

AdminFactory.class: AdminFactory.java
	$(JCC) AdminFactory.java

CustomerFactory.class: CustomerFactory.java
	$(JCC) CustomerFactory.java
	
Invoker.class: Invoker.java
	$(JCC) Invoker.java
	
Marketplace.class: Marketplace.java
	$(JCC) Marketplace.java

MarketplaceDispatcher.class: MarketplaceDispatcher.java
	$(JCC) MarketplaceDispatcher.java

MarketplaceFrontController.class: MarketplaceFrontController.java
	$(JCC) MarketplaceFrontController.java

MarketplaceModel.class: MarketplaceModel.java
	$(JCC) MarketplaceModel.java

MarketplaceServer.class: MarketplaceServer.java
	$(JCC) MarketplaceServer.java
	
MarketplaceView.class: MarketplaceView.java
	$(JCC) MarketplaceView.java
	
UserPage.class: UserPage.java
	$(JCC) UserPage.java
	
clean: 
	$(RM) *.class