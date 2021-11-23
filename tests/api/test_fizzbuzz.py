import os
import requests
import unittest


class TestFizzBuzz(unittest.TestCase):
  def test_ten(self):
    appUrl = os.getenv('APP_URL')
    actual = requests.get(url=f"{appUrl}api/fizzbuzz", params="size=10").text
    expected = '[1,2,"Fizz",4,"Buzz","Fizz",7,8,"Fizz","Buzz"]'
    self.assertEqual(actual, expected)

if __name__ == '__main__':
  unittest.main()
