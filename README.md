# Tournaments for HipChat

## [Swiss pairings](https://en.wikipedia.org/wiki/Swiss-system_tournament)

`/tournament start swiss:2:PT65M:4 "Netrunner Draft #5" @Alice @Bob Carol @Dave @Eve Frank Grace`
> Tournament `T1` started.

`/tournament info`
> Tournament `T1` “Netrunner Draft #5” has started on 2016-05-16 17:35.

> It uses Swiss pairings. Number of rounds: 2. Time for each round: 65 minutes. Byes are worth 4 points.

> Participants: Alice Alpha, Bob Beta, Carol, Dave Delta, Eve Epsilon, Frank, Grace

> It is the current tournament.

`/round start`
> Round `T1R1` started. It will end on 18:40.

> Games to play:
* `T1R1G1: Alice Alpha vs Dave Delta`
* `T1R1G2: Carol vs Eve Epsilon`
* `T1R1G3: Frank vs Bob Beta`

> Bye given to Grace. An odd number of players results in a bye for a random lowest-ranked player.

`/game add-vs @Carol @Eve 0:2`
> Game `T1R1G2` recorded.

`/round start`
> Round `T1R2` cannot start until `T1R1` finishes:
* `T1R1G1: Alice Alpha vs Dave Delta`
* `T1R1G3: Frank vs Bob Beta`

`/game add-vs @Alice @Bob 2:2`
> These opponents should not play against each other this round. Command ignored.

`/game update Carol @Eve 2:2`
> Game `T1R1G2` updated.

`/game show G1`
> Game `T1R1G1: Alice Alpha vs Dave Delta` was not played yet.

`/game show G2`
> Game `T1R1G2: Carol vs Eve Epsilon 2:2` was played.

`/game add-id G1 4:0`
> Game `T1R1G1: Alice Alpha vs Dave Delta 4:0` recored.

_65 minutes pass ..._

> Round `T1R1` timed out. The following games should be timed out:
* `T1R1G3: Frank vs Bob Beta`

`/game add-vs @Bob Frank 1:2`
> Game `T1R1G3: Frank vs Bob Beta 2:1` recorded.

> Round `T1R1` finished.

`/score`
> `T1` current standings:
* Alice Alpha: 4
* Grace: 4
* Carol: 2
* Eve Epsilon: 2
* Frank: 2
* Bob Beta: 1
* Dave: 0

`/round start`
> Round `T1R2` started. It will end on 19:50.

> Games to play:
* `T1R2G1: Alice Alpha vs Grace`
* `T1R2G2: Carol vs Frank`
* `T1R2G3: Bob Beta vs Eve Epsilon`

> Bye given to Dave. An odd number of players results in a bye for a random lowest-ranked player.

`/game add-vs @Bob @Eve 2:2`
> Game `T1R2G3` recorded.

`/game add-vs @Alice Grace 4:0`
> Game `T1R2G1` recorded.

`/game add-vs Carol Frank 0:4`
> Game `T1R2G2` recorded.

> Round `T1R2` finished.

> Tournament `T1` finished.

`/score`
> `T1` current standings:
* Alice Alpha: 8
* Frank: 6
* Dave: 4
* Eve Epsilon: 4
* Grace: 4
* Bob Beta: 3
* Carol: 2
