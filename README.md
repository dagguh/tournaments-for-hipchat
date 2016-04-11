# Tournaments for HipChat

`/tournament start swiss "Netrunner Draft #5" @Alice @Bob @Carol @Dave @Eve @Frank`
> Tournament `T1` started.

`/tournament info`
> Tournament `T1` “Netrunner Draft #5” has started on 2016-05-16 17:35.

> It uses Swiss pairings. Rounds are set to last 65 minutes. There’s no predetermined number of rounds.

> Participants: Alice Alpha, Bob Beta, Carol, Dave Delta, Eve Epsilon, Frank.

> It is the current tournament.

`/round start`
> Round `T1R1` started. It will end on 18:40.

> Games to play:
* `T1R1G1: Alice Alpha vs Dave Delta`
* `T1R1G2: Carol vs Eve Epsilon`
* `T1R1G3: Frank vs Bob Beta`

`/game add @Carol @Eve 0:2`
> Game `T1R1G2` recorded.

`/round start`
> Round `T1R2` cannot start until `T1R1` finishes:
* `T1R1G1: Alice Alpha vs Dave Delta`
* `T1R1G3: Frank vs Bob Beta`

`/game add @Alice @Bob 2:2`
> These opponents should not play against each other this round. Command ignored.

`/game update @Carol @Eve 2:2`
> Game `T1R1G2` updated.

`/game show G1`
> Game `T1R1G1: Alice Alpha vs Dave Delta` was not played yet.

`/game show G2`
> Game `T1R1G2: Carol vs Eve Epsilon 2:2` was played.

`/game add G1 4:0`
> Game `T1R1G1: Alice Alpha vs Dave Delta 4:0` recored.

_65 minutes pass ..._

> Round `T1R1` timed out. The following games should be timed out:
* `T1R1G3: Frank vs Bob Beta`

`/game add @Bob @Frank 1:2`
> Game `T1R1G3: Frank vs Bob Beta 2:1` recorded.

> Round `T1R1` finished. Current standings:
* Alice Alpha: 4
* Carol: 2
* Eve Epsilon: 2
* Frank: 2
* Bob Beta: 1
* Dave: 0

`/round start`
> Round `T1R2` started. It will end on 19:50.

> Games to play:
* `T1R2G1: Alice Alpha vs Carol`
* `T1R2G2: Eve Epsilon vs Frank`
* `T1R2G3: Bob Beta vs Dave`
