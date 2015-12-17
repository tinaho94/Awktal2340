import 'dart:html';

import 'GameState.dart';
import 'PlayerTurnSceneController.dart';
import 'SceneManager.dart';
import 'GameScene.dart';
import 'MapRenderer.dart';
import 'Player.dart';
import 'InventoryRenderer.dart';
import 'Store.dart';
import 'ResourceType.dart';
import 'MuleType.dart';

class StoreController extends PlayerTurnSceneController {

    StoreController(DivElement stage, GameState gameState) : super(stage, gameState) {
        setupListeners();
        listStock();
        stage.querySelector("#exit").onClick.listen((event) => SceneManager.loadScene(GameScene.TOWN_VIEW, gameState));
    }

    void setupListeners() {
        querySelector("#buy_food").onClick.listen((event) => buyResource(ResourceType.FOOD));
        querySelector("#buy_energy").onClick.listen((event) => buyResource(ResourceType.ENERGY));
        querySelector("#buy_ore").onClick.listen((event) => buyResource(ResourceType.ORE));

        querySelector("#sell_food").onClick.listen((event) => sellResource(ResourceType.FOOD));
        querySelector("#sell_energy").onClick.listen((event) => sellResource(ResourceType.ENERGY));
        querySelector("#sell_ore").onClick.listen((event) => sellResource(ResourceType.ORE));

        querySelector("#food_mule").onClick.listen((event) => buyMule(MuleType.FOOD));
        querySelector("#energy_mule").onClick.listen((event) => buyMule(MuleType.ENERGY));
        querySelector("#ore_mule").onClick.listen((event) => buyMule(MuleType.ORE));
    }

    void buyResource(ResourceType r) {
        gameState.store.buyResource(r, 1, gameState.currentPlayer);
        SceneManager.loadScene(GameScene.STORE, gameState);
    }

    void sellResource(ResourceType r) {
        gameState.store.sellResource(r, 1, gameState.currentPlayer);
        SceneManager.loadScene(GameScene.STORE, gameState);
    }

    void listStock() {
        Map<ResourceType, int> stock = gameState.store.inventory.resources;
        querySelector("#food_stock").text = "Food : ${stock[ResourceType.FOOD]}";
        querySelector("#energy_stock").text = "Energy : ${stock[ResourceType.ENERGY]}";
        querySelector("#ore_stock").text = "Ore : ${stock[ResourceType.ORE]}";
        querySelector("#mule_stock").text = "Mules : ${gameState.store.mules.length}";
    }

    void buyMule(MuleType r) {
        gameState.store.buyMule(gameState.currentPlayer, r);
        SceneManager.loadScene(GameScene.WORLD_VIEW, gameState);
    }

}