# pong

A one-player Pong clone written in [Processing](https://processing.org/) (Java mode). It was one of my first programs, written in my junior year of high school.

You control the right paddle. The left paddle is controlled by the computer.

## Running it

1. Install [Processing](https://processing.org/download) (3.x or 4.x).
2. Clone this repo. The folder must stay named `pong`, because Processing needs the sketch folder to match `pong.pde`.
3. Open `pong.pde` in the Processing IDE and press **Run**.

From the command line (with `processing-java` installed):

```sh
processing-java --sketch="$(pwd)" --run
```

## Controls

| Key  | Action                 |
| ---- | ---------------------- |
| `↑`  | Move your paddle up    |
| `↓`  | Move your paddle down  |

The score is shown at the top center: the computer's score on the left, yours on the right.

## How it works

| File         | What it does |
| ------------ | ------------ |
| `pong.pde`   | Main sketch. `setup()` creates an 800×600 window, the ball and both paddles. `draw()` runs every frame: it moves the ball, bounces it off the top and bottom walls, scores a point when it leaves the left or right edge, moves the AI paddle, keeps both paddles on screen, and checks for paddle hits. `keyPressed()`/`keyReleased()` drive the player paddle. |
| `Ball.pde`   | `Ball` class: position, speed, diameter, and `left()`/`right()`/`top()`/`bottom()` edge helpers. |
| `Paddle.pde` | `Paddle` class: position, size, speed, the same edge helpers, and `moveUp()`/`moveDown()` for the AI paddle. |
| `pics/`      | `WhitfieldSeal.png`, a school seal. The sketch doesn't use it at the moment. |

The angle mechanic: when the ball hits **your** paddle, its vertical speed depends on where it hit. `map()` turns the distance from the paddle's center into a vertical speed between -10 and 10, so hits near the edges send the ball off at a steep angle.

## Known quirks

These are bugs in the original code, left as-is:

- **The computer can't lose.** The AI uses `while` loops to step the left paddle until it lines up with the ball, all within a single frame. In effect it teleports, so it never misses.
- **Your paddle moves at double speed.** `paddleRight.move()` and `display()` are each called twice per frame in `draw()`.
- **The ball can get stuck in a paddle.** A hit flips `speedX` without checking which way the ball is moving, so a ball that ends up inside a paddle flips direction every frame and jitters.
- **Serves don't reset.** After a point the ball goes back to the center but keeps its old speed and direction.
- **Only your paddle changes the ball's angle.** The AI paddle just reverses `speedX`.

## License

MIT. See [LICENSE](LICENSE).
