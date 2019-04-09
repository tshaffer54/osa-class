#!/usr/bin/env python3
"""
The *bean machine*, also known as the *Galton Board* or *quincunx*, is a device invented by Sir Francis Galton to
demonstrate the central limit theorem, in particular that the normal distribution is approximate to the binomial
distribution.
"""

import argparse
import random
import threading


class Board:
    """
    Class Board
    
    Contains multiple bins that collect beans
    Contains multiple levels of pegs
    """

    def __init__(self, bins: int):
        """Make a new board of the specified size"""
        self._bins = [0] * bins
        self._pegs = bins // 2

    def status(self, pos: int):
        """Print status"""
        return self._bins[pos]
        # raise NotImplementedError

    def __len__(self):
        """Return the board size"""
        return len(self._bins)

    def __getitem__(self, idx: int):
        """Get number of beans in the specified bin"""
        return self._bins[idx]

    def __setitem__(self, idx: int, new_value: int):
        """Set number of beans in the specified bin"""
        self._bins[idx] = new_value

    @property
    def pegs(self):
        """Return number of levels of pegs"""
        return self._pegs


class Bean(threading.Thread):
    """ Class Bean -- Data members: board, current position, probability, lock """
    def __init__(self, board: object, start: int, prob: float, lock: object):
        """Make a new Bean"""
        super().__init__()
        self.board = board
        self.start = start
        self.prob = prob
        self.lock = lock
        # raise NotImplementedError

    def move_left(self, pos):
        """Move a bean left"""
        beanc = self.board.__getitem__(pos)
        self.board.__setitem__(pos, beanc-1)
        beanc = self.board.__getitem__(pos-1)
        self.board.__setitem__(pos-1, beanc+1)
        return pos-1
        # raise NotImplementedError

    def move_right(self, pos):
        """Move a bean right"""
        beanc = self.board.__getitem__(pos)
        self.board.__setitem__(pos, beanc-1)
        beanc = self.board.__getitem__(pos+1)
        self.board.__setitem__(pos+1, beanc+1)
        return pos+1
        # raise NotImplementedError

    def run(self):
        """Run a bean through the pegs"""
        probs = self.prob * 100
        print(self.board)
        pos = self.start
        for i in range(5):
            numL = random.randint(0, 100)
            numR = random.randint(0, 100)
            if numL < probs:
                self.move_left(pos)
            elif numR < probs:
                self.move_right(pos)
        return self.board
        # raise NotImplementedError


def main():
    """Main function"""
    # Parse command-line arguments
    parser = argparse.ArgumentParser(description="Process the arguments.")

    parser.add_argument('--beans', action='store', type=int, default=1000)
    parser.add_argument('--bins', action='store', type=int, default=11)
    parser.add_argument('--prob', action='store', type=float)
    parser.add_argument('--start', action='store', type=int)
    args = vars(parser.parse_args())
    bean = args['beans']

    print("Start")
    # Create a list of jobs
    jobs = []

    # Create a shared lock
    lock = threading.Lock()

    # Create a board
    board = Board(args['bins'])
    board.__setitem__(args['start'], bean)

    # Create jobs (beans)
    # Print the board status
    stat = ""
    for num in range(0, args['bins']):
        stat += " | " + str(board.status(num))
    stat += " |   " + str(args['beans'])
    print(stat)

    # Start jobs
    # Stop jobs
    for i in range(bean):
        thread = threading.Thread(target=Bean, args=(board, args['start'], args['prob'], lock))
        jobs.append(thread)
        thread.start()
    # Print the board status
    stat = ""
    for num in range(0, args['bins']):
        stat += " | " + str(board.status(num))
    stat += " |   " + str(args['beans'])
    print(stat)

    print("Done")


if __name__ == "__main__":
    main()

