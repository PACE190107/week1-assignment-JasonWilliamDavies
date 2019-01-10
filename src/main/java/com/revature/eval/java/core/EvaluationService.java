package com.revature.eval.java.core;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.time.temporal.Temporal;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.Vector;
import java.util.ArrayList;
import java.util.HashMap;

public class EvaluationService {

	/**
	 * 1. Without using the StringBuilder or StringBuffer class, write a method that
	 * reverses a String. Example: reverse("example"); -> "elpmaxe"
	 * 
	 * @param string
	 * @return
	 */
	public String reverse(String string) {
		char[] reversed = new char[string.length()];
		for (int i = reversed.length - 1, j=0; i >= 0; i--, j++) {
			reversed[j] = string.charAt(i);
		}
		return new String(reversed);
	}

	/**
	 * 2. Convert a phrase to its acronym. Techies love their TLA (Three Letter
	 * Acronyms)! Help generate some jargon by writing a program that converts a
	 * long name like Portable Network Graphics to its acronym (PNG).
	 * 
	 * @param phrase
	 * @return
	 */
	public String acronym(String phrase) {		
		String acronym = "";
		String[] words = phrase.replace(",", "").split("\\W");
		for (int i=0; i<words.length; i++) {
			acronym += words[i].charAt(0);
		}
		return acronym.toUpperCase();
	}

	/**
	 * 3. Determine if a triangle is equilateral, isosceles, or scalene. An
	 * equilateral triangle has all three sides the same length. An isosceles
	 * triangle has at least two sides the same length. (It is sometimes specified
	 * as having exactly two sides the same length, but for the purposes of this
	 * exercise we'll say at least two.) A scalene triangle has all sides of
	 * different lengths.
	 *
	 */
	static class Triangle {
		private double sideOne;
		private double sideTwo;
		private double sideThree;

		public Triangle() {
			super();
		}

		public Triangle(double sideOne, double sideTwo, double sideThree) {
			this();
			this.sideOne = sideOne;
			this.sideTwo = sideTwo;
			this.sideThree = sideThree;
		}

		public double getSideOne() {
			return sideOne;
		}

		public void setSideOne(double sideOne) {
			this.sideOne = sideOne;
		}

		public double getSideTwo() {
			return sideTwo;
		}

		public void setSideTwo(double sideTwo) {
			this.sideTwo = sideTwo;
		}

		public double getSideThree() {
			return sideThree;
		}

		public void setSideThree(double sideThree) {
			this.sideThree = sideThree;
		}

		public boolean isEquilateral() {
			return (sideOne == sideTwo && sideTwo == sideThree);
		}

		public boolean isIsosceles() {
			return (sideOne == sideTwo || sideOne == sideThree || sideTwo == sideThree);
		}

		public boolean isScalene() {
			return (sideOne != sideTwo && sideOne != sideThree && sideTwo != sideThree);
		}

	}

	/**
	 * 4. Given a word, compute the scrabble score for that word.
	 * 
	 * --Letter Values-- Letter Value A, E, I, O, U, L, N, R, S, T = 1; D, G = 2; B,
	 * C, M, P = 3; F, H, V, W, Y = 4; K = 5; J, X = 8; Q, Z = 10; Examples
	 * "cabbage" should be scored as worth 14 points:
	 * 
	 * 3 points for C, 1 point for A, twice 3 points for B, twice 2 points for G, 1
	 * point for E And to total:
	 * 
	 * 3 + 2*1 + 2*3 + 2 + 1 = 3 + 2 + 6 + 3 = 5 + 9 = 14
	 * 
	 * @param string
	 * @return
	 */
	public int getScrabbleScore(String string) {
		HashMap<Character, Integer> alphabet = new HashMap<Character, Integer>();
		alphabet.put('A', 1);
		alphabet.put('B', 3);
		alphabet.put('C', 3);
		alphabet.put('D', 2);
		alphabet.put('E', 1);
		alphabet.put('F', 4);
		alphabet.put('G', 2);
		alphabet.put('H', 4);
		alphabet.put('I', 1);
		alphabet.put('J', 8);
		alphabet.put('K', 5);
		alphabet.put('L', 1);
		alphabet.put('M', 3);
		alphabet.put('N', 1);
		alphabet.put('O', 1);
		alphabet.put('P', 3);
		alphabet.put('Q', 10);
		alphabet.put('R', 1);
		alphabet.put('S', 1);
		alphabet.put('T', 1);
		alphabet.put('U', 1);
		alphabet.put('V', 4);
		alphabet.put('W', 4);
		alphabet.put('X', 8);
		alphabet.put('Y', 4);
		alphabet.put('Z', 10);
		char character;
		int score = 0;
		for (int i=0; i<string.length(); i++) {
			character = string.charAt(i);
			if (alphabet.containsKey(Character.toUpperCase(character))) {
				score += alphabet.get(Character.toUpperCase(character));
			}
		}
		return score;
	}

	/**
	 * 5. Clean up user-entered phone numbers so that they can be sent SMS messages.
	 * 
	 * The North American Numbering Plan (NANP) is a telephone numbering system used
	 * by many countries in North America like the United States, Canada or Bermuda.
	 * All NANP-countries share the same international country code: 1.
	 * 
	 * NANP numbers are ten-digit numbers consisting of a three-digit Numbering Plan
	 * Area code, commonly known as area code, followed by a seven-digit local
	 * number. The first three digits of the local number represent the exchange
	 * code, followed by the unique four-digit number which is the subscriber
	 * number.
	 * 
	 * The format is usually represented as
	 * 
	 * 1 (NXX)-NXX-XXXX where N is any digit from 2 through 9 and X is any digit
	 * from 0 through 9.
	 * 
	 * Your task is to clean up differently formatted telephone numbers by removing
	 * punctuation and the country code (1) if present.
	 * 
	 * For example, the inputs
	 * 
	 * +1 (613)-995-0253 613-995-0253 1 613 995 0253 613.995.0253 should all produce
	 * the output
	 * 
	 * 6139950253
	 * 
	 * Note: As this exercise only deals with telephone numbers used in
	 * NANP-countries, only 1 is considered a valid country code.
	 */
	public String cleanPhoneNumber(String string) {
		int expectedDigitCount = 10;
		// 1 - Check for alphabetical characters
		if (string.contains("[a-zA-Z]")) {
			throw new IllegalArgumentException();
		}
		// 2 - Remove non-numeric characters
		String numbersOnly = string.replaceAll("[^0-9]", "");
		// 3 - Check for invalid size of phone number
		if ((numbersOnly.length() > expectedDigitCount + 1) || numbersOnly.length() < expectedDigitCount) {
			throw new IllegalArgumentException();
		}
		// 4 - Check for invalid area code N value
		if ((numbersOnly.length() == expectedDigitCount + 1 && numbersOnly.charAt(0) == 1
			 && numbersOnly.charAt(1) > 1 && numbersOnly.charAt(1) < 10)
			|| (string.length() == expectedDigitCount && string.charAt(0) == 1)) {
			throw new IllegalArgumentException();
		}
		return numbersOnly;	
	}

	/**
	 * 6. Given a phrase, count the occurrences of each word in that phrase.
	 * 
	 * For example for the input "olly olly in come free" olly: 2 in: 1 come: 1
	 * free: 1
	 * 
	 * @param string
	 * @return
	 */
	public Map<String, Integer> wordCount(String string) {
		String[] words = string.replaceAll("[\\t\\r\\n]+", "").split("\\W");
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		for (int i=0; i<words.length; i++) {
			if (!map.containsKey(words[i])) {
				map.put(words[i], 1);
			}
			else {
				map.replace(words[i], map.get(words[i]) + 1);
			}
		}
		return map;
	}

	/**
	 * 7. Implement a binary search algorithm.
	 * 
	 * Searching a sorted collection is a common task. A dictionary is a sorted list
	 * of word definitions. Given a word, one can find its definition. A telephone
	 * book is a sorted list of people's names, addresses, and telephone numbers.
	 * Knowing someone's name allows one to quickly find their telephone number and
	 * address.
	 * 
	 * If the list to be searched contains more than a few items (a dozen, say) a
	 * binary search will require far fewer comparisons than a linear search, but it
	 * imposes the requirement that the list be sorted.
	 * 
	 * In computer science, a binary search or half-interval search algorithm finds
	 * the position of a specified input value (the search "key") within an array
	 * sorted by key value.
	 * 
	 * In each step, the algorithm compares the search key value with the key value
	 * of the middle element of the array.
	 * 
	 * If the keys match, then a matching element has been found and its index, or
	 * position, is returned.
	 * 
	 * Otherwise, if the search key is less than the middle element's key, then the
	 * algorithm repeats its action on the sub-array to the left of the middle
	 * element or, if the search key is greater, on the sub-array to the right.
	 * 
	 * If the remaining array to be searched is empty, then the key cannot be found
	 * in the array and a special "not found" indication is returned.
	 * 
	 * A binary search halves the number of items to check with each iteration, so
	 * locating an item (or determining its absence) takes logarithmic time. A
	 * binary search is a dichotomic divide and conquer search algorithm.
	 * 
	 */
	static class BinarySearch<T extends Comparable<T>> { // TODO: Can we? <T extend Comparable<T>> Otherwise, how do we compare generics?
		private List<T> sortedList;

		public int indexOf(T t) {
			int min = 0;
			int max = sortedList.size()-1;
			int index;
			while (min <= max) {
				index = (max + min) / 2;
				if (sortedList.get(index).compareTo(t) == 0) {
					return index;
				}
				else if (sortedList.get(index).compareTo(t) < 0) {
					min = index + 1;
				}
				else {
					max = index - 1;
				}
			}			
			return -1;
		}

		public BinarySearch(List<T> sortedList) {
			super();
			this.sortedList = sortedList;
		}

		public List<T> getSortedList() {
			return sortedList;
		}

		public void setSortedList(List<T> sortedList) {
			this.sortedList = sortedList;
		}

	}

	/**
	 * 8. Implement a program that translates from English to Pig Latin.
	 * 
	 * Pig Latin is a made-up children's language that's intended to be confusing.
	 * It obeys a few simple rules (below), but when it's spoken quickly it's really
	 * difficult for non-children (and non-native speakers) to understand.
	 * 
	 * Rule 1: If a word begins with a vowel sound, add an "ay" sound to the end of
	 * the word. Rule 2: If a word begins with a consonant sound, move it to the end
	 * of the word, and then add an "ay" sound to the end of the word. There are a
	 * few more rules for edge cases, and there are regional variants too.
	 * 
	 * See http://en.wikipedia.org/wiki/Pig_latin for more details.
	 * 
	 * @param string
	 * @return
	 */
	public String toPigLatin(String string) {
		String[] words = string.split(" ");
		String output = "";
		String consonants = "bcdfghjklmnpqrstvwzyz";
		int counter;
		for (int i=0; i<words.length; i++) {
			counter = 0;
			for (int j=0; j<words[i].length(); j++) {
				if (consonants.contains(Character.toString(words[i].charAt(j)))) {
					counter++;
					if (words[i].charAt(j) == 'q' && j+1 < words.length && words[i].charAt(j+1) == 'u') {
						counter++;
					}
				} else {
					break;
				}
			}
			output += (words[i].substring(counter, words[i].length()) + words[i].substring(0, counter) + "ay");
			if (i < words.length-1) {
				output += " ";
			}
		}
		return output;
	}

	/**
	 * 9. An Armstrong number is a number that is the sum of its own digits each
	 * raised to the power of the number of digits.
	 * 
	 * For example:
	 * 
	 * 9 is an Armstrong number, because 9 = 9^1 = 9 10 is not an Armstrong number,
	 * because 10 != 1^2 + 0^2 = 2 153 is an Armstrong number, because: 153 = 1^3 +
	 * 5^3 + 3^3 = 1 + 125 + 27 = 153 154 is not an Armstrong number, because: 154
	 * != 1^3 + 5^3 + 4^3 = 1 + 125 + 64 = 190 Write some code to determine whether
	 * a number is an Armstrong number.
	 * 
	 * @param input
	 * @return
	 */
	public boolean isArmstrongNumber(int input) {
		// 1 - Separate the digits 
		int remainingDigits = input;
		int digit = 0;
		Stack<Integer> stack = new Stack<Integer>();
		while (remainingDigits > 0) {
			digit = remainingDigits % 10;
			remainingDigits /= 10;
			stack.push(digit);
		}
		// 2 - Raise each digit to the power of the original number of digits
		int powerValue = stack.size();
		int output = 0;
		while (!stack.isEmpty()) {
			output += Math.pow(stack.pop(), powerValue);
		}
		return (input == output);
	}

	/**
	 * 10. Compute the prime factors of a given natural number.
	 * 
	 * A prime number is only evenly divisible by itself and 1.
	 * 
	 * Note that 1 is not a prime number.
	 * 
	 * @param l
	 * @return
	 */
	public List<Long> calculatePrimeFactorsOf(long l) {
		if (l <= 1) {
			return null;
		}
		// 1 - Find all the factors of l
		List<Long> factors = getFactors(l);
		List<Long> primeFactors = new ArrayList<Long>();
		// 2 - Reduce the list of factors down to prime values
		for (Long factor : factors) {
			if (isPrime(factor)) {
				primeFactors.add(factor);
			}
		}
		return primeFactors;
	}
	
	public List<Long> getFactors(long input) {
		List<Long> factors = new ArrayList<Long>();
		long remainder = input;
		long i = 2;
		while (i <= remainder) {
			if ((remainder % i) == 0) {
				remainder /= i;
				factors.add(i);
			} else {
				i++;
			}
		}
		return factors;
	}

	public boolean isPrime(long input) {
		for (long i=2; i<=input/2l; i++) {
			if ((input % i) == 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 11. Create an implementation of the rotational cipher, also sometimes called
	 * the Caesar cipher.
	 * 
	 * The Caesar cipher is a simple shift cipher that relies on transposing all the
	 * letters in the alphabet using an integer key between 0 and 26. Using a key of
	 * 0 or 26 will always yield the same output due to modular arithmetic. The
	 * letter is shifted for as many values as the value of the key.
	 * 
	 * The general notation for rotational ciphers is ROT + <key>. The most commonly
	 * used rotational cipher is ROT13.
	 * 
	 * A ROT13 on the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: nopqrstuvwxyzabcdefghijklm It is
	 * stronger than the Atbash cipher because it has 27 possible keys, and 25
	 * usable keys.
	 * 
	 * Ciphertext is written out in the same formatting as the input including
	 * spaces and punctuation.
	 * 
	 * Examples: ROT5 omg gives trl ROT0 c gives c ROT26 Cool gives Cool ROT13 The
	 * quick brown fox jumps over the lazy dog. gives Gur dhvpx oebja sbk whzcf bire
	 * gur ynml qbt. ROT13 Gur dhvpx oebja sbk whzcf bire gur ynml qbt. gives The
	 * quick brown fox jumps over the lazy dog.
	 */
	static class RotationalCipher {
		private int key;

		public RotationalCipher(int key) {
			super();
			this.key = key;
		}

		public String rotate(String string) {
			int lowerCaseLBound = 'a';
			int upperCaseLBound = 'A';
			int lowerCaseRBound = 'z';
			int upperCaseRBound = 'Z';
			char[] characters = string.toCharArray();
			for (int i=0; i<characters.length; i++) {
				if (Character.isAlphabetic(characters[i])) {
					if (Character.isLowerCase(characters[i])) {	
						characters[i] += key;
						if (characters[i] > lowerCaseRBound) {
							characters[i] %= lowerCaseRBound;
							characters[i] += (lowerCaseLBound - 1);	
						}
					} else if (Character.isUpperCase(characters[i])) {
						characters[i] += key;
						if (characters[i] > upperCaseRBound) {
							characters[i] %= upperCaseRBound;
							characters[i] += (upperCaseLBound - 1);	
						}
					}
				}
			}
			return String.valueOf(characters);
		}
	}

	/**
	 * 12. Given a number n, determine what the nth prime is.
	 * 
	 * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see
	 * that the 6th prime is 13.
	 * 
	 * If your language provides methods in the standard library to deal with prime
	 * numbers, pretend they don't exist and implement them yourself.
	 * 
	 * @param i
	 * @return
	 */
	public int calculateNthPrime(int n) {
		if (n <= 0) {
			throw new IllegalArgumentException();
		}
		int primeCounter = 0;
		int index = 1;
		while (primeCounter < n) {
			++index;
			if (isPrime(index)) {
				primeCounter++;
			}
		}
		return index;	
	}
	
	public boolean isPrime(int input) {
		for (int i=2; i<= input/2; i++) {
			if ((input % i) == 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 13 & 14. Create an implementation of the atbash cipher, an ancient encryption
	 * system created in the Middle East.
	 * 
	 * The Atbash cipher is a simple substitution cipher that relies on transposing
	 * all the letters in the alphabet such that the resulting alphabet is
	 * backwards. The first letter is replaced with the last letter, the second with
	 * the second-last, and so on.
	 * 
	 * An Atbash cipher for the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: zyxwvutsrqponmlkjihgfedcba It is a
	 * very weak cipher because it only has one possible key, and it is a simple
	 * monoalphabetic substitution cipher. However, this may not have been an issue
	 * in the cipher's time.
	 * 
	 * Ciphertext is written out in groups of fixed length, the traditional group
	 * size being 5 letters, and punctuation is excluded. This is to make it harder
	 * to guess things based on word boundaries.
	 * 
	 * Examples Encoding test gives gvhg Decoding gvhg gives test Decoding gsvjf
	 * rxpyi ldmul cqfnk hlevi gsvoz abwlt gives thequickbrownfoxjumpsoverthelazydog
	 *
	 */
	static class AtbashCipher {

		/**
		 * Question 13
		 * 
		 * @param string
		 * @return
		 */
		public static String encode(String string) {
			char alphabetLBound = 'a';
			char alphabetRBound = 'z';
			// Remove all non-alphanumeric characters
			char[] input = string.toLowerCase().replaceAll("\\W", "").toCharArray();
			String output = "";
			for (int i=0; i<input.length; i++) {
				// Every 5 characters, add a space
				if (i % 5 == 0 && i != 0) {
					output += ' ';
				} 
				// Numbers are not rotated
				if (Character.isAlphabetic(input[i])) {
					output += (char)(alphabetRBound - input[i] + alphabetLBound);	
				} else {
					output += input[i]; 
				}
				
			}
			return output;
		}

		/**
		 * Question 14
		 * 
		 * @param string
		 * @return
		 */
		public static String decode(String string) {
			char alphabetLBound = 'a';
			char alphabetRBound = 'z';
			// Remove all spaces
			char[] input = string.replaceAll(" ", "").toCharArray();
			String output = "";
			for (int i=0; i<input.length; i++) {
				// Numbers are not rotated
				if (Character.isAlphabetic(input[i])) {
					output += (char)(alphabetLBound + alphabetRBound - input[i]);	
				} else {
					output += input[i];
				}				
			}
			return output;
		}
	}

	/**
	 * 15. The ISBN-10 verification process is used to validate book identification
	 * numbers. These normally contain dashes and look like: 3-598-21508-8
	 * 
	 * ISBN The ISBN-10 format is 9 digits (0 to 9) plus one check character (either
	 * a digit or an X only). In the case the check character is an X, this
	 * represents the value '10'. These may be communicated with or without hyphens,
	 * and can be checked for their validity by the following formula:
	 * 
	 * (x1 * 10 + x2 * 9 + x3 * 8 + x4 * 7 + x5 * 6 + x6 * 5 + x7 * 4 + x8 * 3 + x9
	 * * 2 + x10 * 1) mod 11 == 0 If the result is 0, then it is a valid ISBN-10,
	 * otherwise it is invalid.
	 * 
	 * Example Let's take the ISBN-10 3-598-21508-8. We plug it in to the formula,
	 * and get:
	 * 
	 * (3 * 10 + 5 * 9 + 9 * 8 + 8 * 7 + 2 * 6 + 1 * 5 + 5 * 4 + 0 * 3 + 8 * 2 + 8 *
	 * 1) mod 11 == 0 Since the result is 0, this proves that our ISBN is valid.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isValidIsbn(String string) {
		char[] characters = string.replaceAll("\\W", "").toCharArray();
		int output = 0;
		for (int i=0; i<characters.length; i++) {
			if (Character.isAlphabetic(characters[i])) {
				if (characters[i] == 'X') {
					output += (10 * (10  - i));
				} else { // a character other than X -> invalid
					return false;
				}
			} else {
				output += (Character.getNumericValue(characters[i]) * (10  - i));
			}
		}
		return (output % 11 == 0);
	}

	/**
	 * 16. Determine if a sentence is a pangram. A pangram (Greek: παν γράμμα, pan
	 * gramma, "every letter") is a sentence using every letter of the alphabet at
	 * least once. The best known English pangram is:
	 * 
	 * The quick brown fox jumps over the lazy dog.
	 * 
	 * The alphabet used consists of ASCII letters a to z, inclusive, and is case
	 * insensitive. Input will not contain non-ASCII symbols.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isPangram(String string) {
		int alphabetSize = 26;
		int alphabetLBound = 'a';
		boolean[] charactersFound = new boolean[alphabetSize];
		char[] characters = string.replaceAll("[^a-z]", "").toCharArray();	
		for (int i=0; i<characters.length; i++) {
			charactersFound[characters[i] - alphabetLBound] = true;
		}
		for (int i=0; i<charactersFound.length; i++) {
			if (!charactersFound[i]) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 17. Calculate the moment when someone has lived for 10^9 seconds.
	 * 
	 * A gigasecond is 109 (1,000,000,000) seconds.
	 * 
	 * @param given
	 * @return
	 */
	public Temporal getGigasecondDate(Temporal given) {
		Temporal output = null;
		if (given instanceof LocalDate) {
			output = LocalDateTime.of(given.get(ChronoField.YEAR),
								  given.get(ChronoField.MONTH_OF_YEAR), 
								  given.get(ChronoField.DAY_OF_MONTH),
								  0, 0, 0);	
		} else if (given instanceof LocalDateTime) {
			output = LocalDateTime.of(given.get(ChronoField.YEAR),
					  				  given.get(ChronoField.MONTH_OF_YEAR), 
					  				  given.get(ChronoField.DAY_OF_MONTH),
					  				  ((LocalDateTime) given).getHour(),
					  				  ((LocalDateTime) given).getMinute(),
					  				  ((LocalDateTime) given).getSecond()
					 				 );
		}
		return ((LocalDateTime)output).plusSeconds(1000000000);
	}

	/**
	 * 18. Given a number, find the sum of all the unique multiples of particular
	 * numbers up to but not including that number.
	 * 
	 * If we list all the natural numbers below 20 that are multiples of 3 or 5, we
	 * get 3, 5, 6, 9, 10, 12, 15, and 18.
	 * 
	 * The sum of these multiples is 78.
	 * 
	 * @param i
	 * @param set
	 * @return
	 */
	public int getSumOfMultiples(int i, int[] set) {
		ArrayList<Integer> multiples = new ArrayList<Integer>();
		int output = 0;
		int counter;
		for (int j=0; j<set.length; j++) {
			counter = 1;
			while (set[j] * counter < i) {
				if (!multiples.contains(set[j] * counter)) {
					multiples.add(set[j] * counter);
				}
				counter++;
			}
		}
		for (Integer a : multiples) {
			output += a;
		}
		return output;
	}

	/**
	 * 19. Given a number determine whether or not it is valid per the Luhn formula.
	 * 
	 * The Luhn algorithm is a simple checksum formula used to validate a variety of
	 * identification numbers, such as credit card numbers and Canadian Social
	 * Insurance Numbers.
	 * 
	 * The task is to check if a given string is valid.
	 * 
	 * Validating a Number Strings of length 1 or less are not valid. Spaces are
	 * allowed in the input, but they should be stripped before checking. All other
	 * non-digit characters are disallowed.
	 * 
	 * Example 1: valid credit card number 1 4539 1488 0343 6467 The first step of
	 * the Luhn algorithm is to double every second digit, starting from the right.
	 * We will be doubling
	 * 
	 * 4_3_ 1_8_ 0_4_ 6_6_ If doubling the number results in a number greater than 9
	 * then subtract 9 from the product. The results of our doubling:
	 * 
	 * 8569 2478 0383 3437 Then sum all of the digits:
	 * 
	 * 8+5+6+9+2+4+7+8+0+3+8+3+3+4+3+7 = 80 If the sum is evenly divisible by 10,
	 * then the number is valid. This number is valid!
	 * 
	 * Example 2: invalid credit card number 1 8273 1232 7352 0569 Double the second
	 * digits, starting from the right
	 * 
	 * 7253 2262 5312 0539 Sum the digits
	 * 
	 * 7+2+5+3+2+2+6+2+5+3+1+2+0+5+3+9 = 57 57 is not evenly divisible by 10, so
	 * this number is not valid.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isLuhnValid(String string) {
		// 1 - Strip spaces
		String inputRemovedSpace = string.replaceAll(" ", "");
		// 2 - Check for invalid characters and length
		if (inputRemovedSpace.contains("[^0-9]") || inputRemovedSpace.length() <= 1) {
			return false;
		}
		// 3 - Perform the Luhn Algorithm
		char[] characters = inputRemovedSpace.toCharArray();
		boolean doubleNextDigit = false;
		int value = 0;
		int totalSum = 0;
		for (int i=characters.length-1; i>=0; i--) {
			value = Character.getNumericValue(characters[i]);
			if (doubleNextDigit) {
				value *= 2;
				if (value > 9) {
					value -= 9;	
				}
			}
			totalSum += value;
			doubleNextDigit = !doubleNextDigit;
		}
		// 4 - Check for validity
		if (totalSum % 10 != 0) {
			return false;
		}
		return true;
	}

	/**
	 * 20. Parse and evaluate simple math word problems returning the answer as an
	 * integer.
	 * 
	 * Add two numbers together.
	 * 
	 * What is 5 plus 13?
	 * 
	 * 18
	 * 
	 * Now, perform the other three operations.
	 * 
	 * What is 7 minus 5?
	 * 
	 * 2
	 * 
	 * What is 6 multiplied by 4?
	 * 
	 * 24
	 * 
	 * What is 25 divided by 5?
	 * 
	 * 5
	 * 
	 * @param string
	 * @return
	 */
	public int solveWordProblem(String string) {
		String[] words = string.replace("?", "").split(" ");	
		boolean foundValueA = false;
		int valueA = 0;
		int valueB = 0;
		for (int i=0; i<words.length; i++) {
			// If we have a word and not an integer...
			if (Character.isAlphabetic(words[i].charAt(words[i].length()-1))) {
				continue;
			} else if (!foundValueA) {
				valueA = Integer.parseInt(words[i]);
				foundValueA = true;	
			} else {
				valueB = Integer.parseInt(words[i]);
			}
		}
		// Seek words, plus, minus, divided, multiplied
		if (string.contains("plus")) {
			return valueA + valueB;
		} else if (string.contains("minus")) {
			return valueA - valueB;
		} else if (string.contains("divided")) {
			return valueA / valueB;
		} else {
			return valueA * valueB;
		}
	}

}