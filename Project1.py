class Random:
	def __init__(self, seed):
		self.n0 = seed
	def next(self, range):
		self.n0 = (7**5*self.n0)%(2**31-1)
		return self.n0 % range
	def choose(self, objects):
		return objects[self.next(len(objects))]
class Dissociated:
	def __init__(self, words):
		self.wordlist = listfy(words)
		self.worddict = {}
		for index in range(len(self.wordlist)):
			if self.wordlist[index] in self.worddict:
				self.worddict[self.wordlist[index]] += [self.wordlist[index + 1 : ]]
			else:
				self.worddict[self.wordlist[index]] = [self.wordlist[index + 1 : ]]
	def run(self, seed):
		self.count = 0
		r = Random(seed)
		i = 0
		self.currentpiece = self.wordlist
		while True:
			self.show(self.currentpiece[i])
			if i == len(self.currentpiece) - 1:
				break
			if r.next(2):
				self.currentlist = self.worddict[self.currentpiece[i]]
				self.currentpiece = r.choose(self.currentlist)
				i = 0
			else:
				i += 1
		self.finish()			
	def show(self, word):
		self.count += len(word) + 1		
		if self.count > 79:
			print '\n',
			self.count = len(word) + 1
		print word,
	def finish(self):
		if self.count < 79:
			print '\n',

def listfy(text):
	wordlist = []
	while True:
		first, remainder = split(text)
		wordlist += [first]
		text = remainder
		if remainder == '':
			return wordlist
		
def split(text):
	i = 0
	firstone = ''
	while True:
		firstone += text[i]
		i += 1
		if text[i] == ' ':
			return (firstone, text[i + 1 : ])

diss =                                                         \
 Dissociated(                                                  \
  "it is a testament to the original computer hackers " +      \
  "prodigious skill that later programmers including " +       \
  "richard m stallman aspired to wear the same hacker " +      \
  "mantle. by the mid to late 1970s the term hacker had " +    \
  "acquired elite connotations. in a general sense a " +       \
  "computer hacker was any person who wrote software code " +  \
  "for the sake of writing software code. in the " +           \
  "particular sense, however it was a testament to " +         \
  "programming skill. like the term artist the meaning " +     \
  "carried tribal overtones. to describe a fellow " +          \
  "programmer as hacker was a sign of respect. to describe " + \
  "oneself as a hacker was a sign of immense personal " +      \
  "confidence. either way the original looseness of the " +    \
  "computer hacker appellation diminished as computers " +     \
  "became more common. ")
diss.run(13)
print '\n'
diss2 = Dissociated("we have nice TAs in 1913. courses other than 1913 do not have such nice TAs. ")
diss2.run(10)