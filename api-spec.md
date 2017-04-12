# Tic Toc Toe Game API API documentation version 1.0

---

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

### /game/{gameId}

* **Game ID**: An identifier which uniquely identifies a Game
    * Type: string
    
    * Required: true

#### **GET**:
Get the state of the Game identified by the gameId

### Response code: 200

#### application/json (application/json) 

```
{
  "board": [
        ["x", "", "o"],
        ["x", "", ""],
        ["o", "", "o"]
  ],
  "position": "IN_PROGRESS"
}
 ```

##### *application/json*:
| Name | Type | Description | Required | Pattern |
|:-----|:----:|:------------|:--------:|--------:|

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

##### *application/json*:
| Name | Type | Description | Required | Pattern |
|:-----|:----:|:------------|:--------:|--------:|

---

### /game/{gameId}/board/{symbol}

* **Game ID**: An identifier which uniquely identifies a Game
    * Type: string
    
    * Required: true

* **Symbol**: Symbol
    * Type: string
    
    * Required: true

#### **PUT**:

#### application/json (application/json) 

```
{
  "location": {
    "row": 1, "column": 1
  }
}
 ```

##### *application/json*:
| Name | Type | Description | Required | Pattern |
|:-----|:----:|:------------|:--------:|--------:|

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

##### *application/json*:
| Name | Type | Description | Required | Pattern |
|:-----|:----:|:------------|:--------:|--------:|

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

##### *application/json*:
| Name | Type | Description | Required | Pattern |
|:-----|:----:|:------------|:--------:|--------:|

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

##### *application/json*:
| Name | Type | Description | Required | Pattern |
|:-----|:----:|:------------|:--------:|--------:|

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

##### *application/json*:
| Name | Type | Description | Required | Pattern |
|:-----|:----:|:------------|:--------:|--------:|

---

