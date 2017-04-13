# Tic-Tac-Toe REST API

This API lets you play Tic-Tac-Toe on 3x3 game board with a friend. It also supports multiple simultaneous games.
It's written in Java 8 with the help of Spring Boot

## Game Rules
- The game is played on a 3x3 grid.
- Players alternate to place their mark on an unoccupied space on the grid.
- The objective is to get three of their marks in a row.
- The first player uses X the second player uses O.
- Play continues until a player gets three of their marks in a row
  (horizontally or diagonally) or there are no free spaces left on the grid.

# API documentation version 1.0
---
**Note:** To see the HTML version of the spec, please look at *api-specification.html* and see you see the RAML version, please look at *src/main/resources/api-specification/application.raml*

## /game

### /game

#### **POST**:
Start a new Game with an empty 3x3 board

### Response code: 201

###### Headers

| Name | Type | Description | Required | Examples |
|:-----|:----:|:------------|:--------:|---------:|
| Location | string | Resource path to the new Game | true | ``` /game/{gameId} ```  |

---

### /game/{gameId}/state

* **Game ID**: An identifier which uniquely identifies a Game
    * Type: string

    * Required: true

#### **GET**:
Use this resource if you want to know the current state of the Game

### Response code: 200

#### application/json (application/json)

```
{
  "state": "IN_PROGRESS"
}
 ```

### Response code: 404

#### application/json (application/json)

```
{
  "errors": [
    {
      "code": "GAME_NOT_FOUND",
      "message": "Game not found"
    }
  ]
}
 ```

---

### /game/{gameId}/board/{symbol}

* **Game ID**: An identifier which uniquely identifies a Game
    * Type: string

    * Required: true

* **symbol**:
    * Type: string

    * Required: true

#### **PUT**:
Use this resource to place the given symbol on the Game board

#### application/json (application/json)

```
{
  "location": {
    "row": 1, "column": 1
  }
}
 ```

### Response code: 204

### Response code: 400

#### application/json (application/json)

```
{
  "errors": [
    {
      "code": "INVALID_VALUE",
      "message": "Row is invalid",
      "path": "/row"
    }
  ]
}
 ```

### Response code: 403

#### application/json (application/json)

```
{
  "errors": [
    {
      "code": "GAME_OVER",
      "message": "Game is over"
    }
  ]
}
 ```

### Response code: 404

#### application/json (application/json)

```
{
  "errors": [
    {
      "code": "GAME_NOT_FOUND",
      "message": "Game not found"
    }
  ]
}
 ```

### Response code: 409

#### application/json (application/json)

```
{
  "errors": [
    {
      "code": "OCCUPIED_LOCATION",
      "message": "Location is already occupied by another player"
    }
  ]
}
 ```

---

