JCC = javac

default: AbstractFactory.class AdminFactory.class AuthorizationException.class AuthorizationInvocationHandler.class CustomerFactory.class Invoker.class ItemController.class Marketplace.class MarketplaceClient.class MarketplaceDispatcher.class MarketplaceFrontController.class MarketplaceModel.class MarketplaceServer.class MarketplaceView.class RequiresRole.class Session.class UserType.class UserPage.class

AbstractFactory.class: AbstractFactory.java
	$(JCC) AbstractFactory.java

AdminFactory.class: AdminFactory.java
	$(JCC) AdminFactory.java

AuthorizationException.class: AuthorizationException.java
	$(JCC) AuthorizationException.java
	
AuthorizationInvocationHandler.class: AuthorizationInvocationHandler.java
	$(JCC) AuthorizationInvocationHandler.java
	
CustomerFactory.class: CustomerFactory.java
	$(JCC) CustomerFactory.java

Invoker.class: Invoker.java
	$(JCC) Invoker.java
	
ItemController.class: ItemController.java
	$(JCC) ItemController.java
	
Marketplace.class: Marketplace.java
	$(JCC) Marketplace.java

MarketplaceClient.class: MarketplaceClient.java
	$(JCC) MarketplaceClient.java
	
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

RequiresRole.class: RequiresRole.java
	$(JCC) RequiresRole.java
	
Session.class: Session.java
	$(JCC) Session.java
	
UserType.class: UserType.java
	$(JCC) UserType.java
	
UserPage.class: UserPage.java
	$(JCC) UserPage.java
	
clean: 
	$(RM) *.class